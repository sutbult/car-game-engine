package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationImage;
import se.chalmers.tda367_4.geometry.Vector2;

public class Car implements ImageEntity {
    private final static Vector2 CAR_SIZE = new Vector2(50, 75);
    private ApplicationEnvironment env;
    private ApplicationImage image;

    public Car(ApplicationEnvironment env) {
        this.env = env;
        image = env.getGraphics().loadImage("orange");
    }
    public ApplicationImage getImage() {
        return image;
    }
    public Vector2 getPosition() {
        return new Vector2(100, 100);
    }

    public Vector2 getBounds() {
        return CAR_SIZE;
    }

    public float getRotation() {
        return 0;
    }
    public void move(float delta) {
        // TODO: Implement
    }
}
