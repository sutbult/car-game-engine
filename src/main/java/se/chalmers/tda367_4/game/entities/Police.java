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
        Vector2 nagontingfint = playerPosition.normalize();
        Vector2 nagontingfint2 = policePosition.normalize();
        float r = (float) Math.acos(nagontingfint.dot(nagontingfint2));
        float x1 = playerPosition.getX();
        float x2 = policePosition.getX();
        float y1 = playerPosition.getY();
        float y2 = policePosition.getY();
        float rotation = (float) Math.atan((x2-x1)/(y2-y1));
        rotation = (float) Math.toRadians(rotation);
        System.out.println(r);
        //float r = rotation - this.getRotation();
        boolean forward = false;
        boolean backward = false;
        boolean left = false;
        boolean right = false;
        float pi = (float) Math.PI;
        if (r <= pi/2 && r >= -pi/2) {
            forward = true;
        } else backward = true;
        if (r != 0) {
            if (r <= pi/2 && r > 0 || r <= -pi/2 && r >= -pi) {
                left = true;
            } else right = true;
        }
        /*while (r < -Math.PI)
            r += 2*Math.PI;
        while (r > Math.PI)
            r -= 2*Math.PI;*/
        return Direction.toDirection(forward,backward,left,right);
    }
}
