package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.game.Direction;
import se.chalmers.tda367_4.geometry.Vector2;


public class Police extends Car {
    private final static float PI = (float)Math.PI;
    private final static float BORDER = PI / 20;
    private float reverseDuration = -1;

    private Car player;

    public Police(ApplicationEnvironment env, Car player) {
        super(env);
        this.player = player;
    }

    @Override
    public void revert() {
        super.revert();
        if (reverseDuration < 0) {
            reverseDuration = 0.9f;
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
