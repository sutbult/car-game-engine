package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationInput;
import se.chalmers.tda367_4.app.ApplicationKey;
import se.chalmers.tda367_4.game.entities.utils.Direction;

public class Player extends Car {
    private ApplicationEnvironment env;

    public Player(ApplicationEnvironment env) {
        super("car_yellow.png");
        this.env = env;
    }
    protected Direction getDirection() {
        ApplicationInput i = env.getInput();
        return Direction.toDirection(
                i.isKeyDown(ApplicationKey.UP),
                i.isKeyDown(ApplicationKey.DOWN),
                i.isKeyDown(ApplicationKey.LEFT),
                i.isKeyDown(ApplicationKey.RIGHT)
        );
    }
}
