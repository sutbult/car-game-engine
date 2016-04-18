package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.geometry.Triangle;
import se.chalmers.tda367_4.geometry.Vector2;

public class Obstacle extends Environment {

    public Obstacle (Vector2 a, Vector2 b, Vector2 c, float red, float green, float blue) {
        super(a, b, c, red, green, blue);
    }

    public Obstacle (Triangle triangle, float red, float green, float blue) {
        super(triangle, red, green, blue);
    }

}
