package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationImage;
import se.chalmers.tda367_4.game.Direction;
import se.chalmers.tda367_4.geometry.Vector2;

public abstract class Car implements ImageEntity {
    private final static float SPEED = 100;
    private final static float TURNING = -0.5f;
    private final static Vector2 CAR_SIZE = new Vector2(100, 50);
    private ApplicationImage image;
    private Vector2 position;
    private float rotation;

    public Car(ApplicationEnvironment env) {
        image = env.getGraphics().loadImage("orange");
        position = new Vector2(500, 500);
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

    private Vector2 getBodyDirection() {
        return Vector2.fromAngle(rotation).multiply(CAR_SIZE.getX());
    }
    public float getRotation() {
        return rotation;
    }
    public void move(float delta) {
        Direction movement = getDirection();
        float turning = movement.getX() * TURNING;
        float speed = movement.getY() * SPEED;

        Vector2 pull = Vector2.fromAngle(rotation + turning).multiply(speed * delta);
        Vector2 direction = getBodyDirection().add(pull);
        Vector2 newDirection = direction.normalize().multiply(CAR_SIZE.getX());
        Vector2 posDiff = direction.subtract(newDirection);
        position = position.add(posDiff);
        rotation = direction.direction();
    }
    protected abstract Direction getDirection();
}
