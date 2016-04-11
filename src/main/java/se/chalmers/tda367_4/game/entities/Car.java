package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationImage;
import se.chalmers.tda367_4.geometry.Vector2;

public class Car implements ImageEntity {
    private final static Vector2 CAR_SIZE = new Vector2(100, 50);
    private ApplicationEnvironment env;
    private ApplicationImage image;
    private Vector2 position;
    private float rotation;
    private float speed;
    private float turning;

    public Car(ApplicationEnvironment env) {
        this.env = env;
        image = env.getGraphics().loadImage("orange");
        position = new Vector2(500, 500);
        rotation = (float)Math.PI / 6;
        rotation = 0;
        speed = 100;
        turning = -.5f;
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

    private Vector2 getBodyDirection() {
        return Vector2.fromAngle(rotation).multiply(CAR_SIZE.getX());
    }
    public float getRotation() {
        return rotation;
    }
    public void move(float delta) {
        Vector2 pull = Vector2.fromAngle(rotation + turning).multiply(speed * delta);
        Vector2 direction = getBodyDirection().add(pull);
        Vector2 newDirection = direction.normalize().multiply(CAR_SIZE.getX());
        Vector2 posDiff = direction.subtract(newDirection);
        position = position.add(posDiff);
        rotation = direction.direction();
    }
}
