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
    private Police police;

    public Police(ApplicationEnvironment env, Car player, Police police) {
        super(env);
        this.player = player;
        this.police = police;
    }

    protected Direction getDirection() {
        Vector2 playerPosition = player.getPosition();
        Vector2 policePosition = police.getPosition();
        float rotation = police.getRotation();
        return Direction.toDirection();
    }
}
