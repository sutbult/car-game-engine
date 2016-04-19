package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.game.Direction;
import se.chalmers.tda367_4.game.GameApplication;
import se.chalmers.tda367_4.geometry.Vector2;

/**
 * Created by antonlevholm on 14/04/16.
 */

public class Police extends Car {
    private Car player;

    public Police(ApplicationEnvironment env, Car player) {
        super(env);
        this.player = player;
    }

    protected Direction getDirection() {
        Vector2 playerPosition = player.getPosition();
        Vector2 policePosition = this.getPosition();
        float rotation = this.getRotation();
        return Direction.toDirection();
    }
}
