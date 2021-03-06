package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.game.entities.utils.Direction;
import se.chalmers.tda367_4.geometry.vector.Vector2;


public class Police extends Car {
    private float reverseDuration = -1;
    private final static float PI = (float)Math.PI;
    private final static float BORDER = PI / 20;

    private Car player;

    public Police(Car player, Multiplier speed) {
        super("car_3_blue.png", speed);
        this.player = player;
    }

    @Override
    public void revert() {
        super.revert();
        if (reverseDuration < 0) {
            reverseDuration = 1.2f;
        } else {
            reverseDuration = -1;
        }
    }

    @Override
    public void move(float delta) {
        if (reverseDuration < 0) {
            super.move(delta);
        } else {
            super.move(-delta);
            reverseDuration -= delta;
        }
    }

    public Direction getDirection() {
        Vector2 playerPosition = player.getPosition();
        Vector2 policePosition = this.getPosition();

        float r = playerPosition
                .subtract(policePosition)
                .direction();
        r -= getRotation();

        while (r < -PI)
            r += 2*PI;
        while (r > PI)
            r -= 2*PI;

        if(r > BORDER) {
            return Direction.FORWARD_LEFT;
        }
        else if(r < BORDER) {
            return Direction.FORWARD_RIGHT;
        }
        else {
            return Direction.FORWARD;
        }
    }
}
