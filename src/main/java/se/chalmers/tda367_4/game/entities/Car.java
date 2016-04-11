package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationImage;
import se.chalmers.tda367_4.geometry.Vector2;

public class Car implements ImageEntity {
    private final static Vector2 CAR_SIZE = new Vector2(50, 75);
    private ApplicationEnvironment env;
    private ApplicationImage image;
    private Vector2 position;
    private float rotation;

    public Car(ApplicationEnvironment env) {
        this.env = env;
        image = env.getGraphics().loadImage("orange");
        position = new Vector2(100, 100);
        rotation = 0;
    }
    public ApplicationImage getImage() {
        return image;
    }
    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getBounds() {
        return CAR_SIZE;
    }

    private Vector2[] getEnds() {
        Vector2[] ends = new Vector2[2];
        ends[0] = new Vector2(
                (float)Math.sin(rotation),
                (float)Math.cos(rotation)
        ).multiply(CAR_SIZE.getY() / 2);
        return ends;
    }
    public float getRotation() {
        return 0;
    }
    public void move(float delta) {
        // TODO: Implement
    }
}
