package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.game.Direction;

public class Player extends Car {
    private ApplicationEnvironment env;

    public Player(ApplicationEnvironment env) {
        super(env);
        this.env = env;
    }
    protected Direction getDirection() {
        return Direction.BACK_RIGHT;
    }
}
