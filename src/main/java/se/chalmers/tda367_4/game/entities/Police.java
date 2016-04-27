package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.game.Direction;
import se.chalmers.tda367_4.geometry.Vector2;


public class Police extends Car {
    private Car player;

    public Police(ApplicationEnvironment env, Car player) {
        super(env);
        this.player = player;
    }

    public Direction getDirection() {
        Vector2 playerPosition = player.getPosition();
        Vector2 policePosition = this.getPosition();
        Vector2 rotation = playerPosition.subtract(policePosition);
        float r = rotation.direction(); r -= getRotation();
        float PI = (float) Math.PI;
        while (r < -PI)
            r += 2*PI;
        while (r > PI)
            r -= 2*PI;
        boolean forward = true;
        boolean backward = false;
        boolean left = false;
        boolean right = false;
        float BORDER = PI / 50;
        if(r > BORDER)
            left = true;
        else if(r < -BORDER)
            right = true;
        return Direction.toDirection(forward,backward,left,right);
    }
}
