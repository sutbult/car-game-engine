package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationImage;
import se.chalmers.tda367_4.game.Direction;
import se.chalmers.tda367_4.geometry.Triangle;
import se.chalmers.tda367_4.geometry.TriangleImpl;
import se.chalmers.tda367_4.geometry.Vector2;

public abstract class Car implements ImageEntity, SolidEntity {
    private final static float SPEED = 5;
    private final static float TURN_RATE = -0.5f;
    private final static Vector2 CAR_SIZE = new Vector2(2, 1);
    private ApplicationImage image;
    private Vector2 position;
    private float rotation;
    private Triangle[] triangles;

    public Car(ApplicationEnvironment env) {
        image = env.getGraphics().loadImage("orange");
        position = new Vector2(0, 0);
        rotation = 0;
        Vector2 upperRight = new Vector2(position.getX() + CAR_SIZE.getX() / 2, position.getY() - CAR_SIZE.getY() / 2);
        Vector2 upperLeft = new Vector2(position.getX() - CAR_SIZE.getX() / 2, position.getY() - CAR_SIZE.getY() / 2);
        Vector2 lowerRight = new Vector2(position.getX() + CAR_SIZE.getX() / 2, position.getY() + CAR_SIZE.getY() / 2);
        Vector2 lowerLeft = new Vector2(position.getX() - CAR_SIZE.getX() / 2, position.getY() + CAR_SIZE.getY() / 2);

        this.triangles = new Triangle[] {new TriangleImpl(upperLeft, upperRight, lowerLeft),
                new TriangleImpl(lowerLeft, upperRight, lowerRight)};
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
        float turning = movement.getX() * TURN_RATE;
        float speed = movement.getY() * SPEED;

        Vector2 pull = Vector2.fromAngle(rotation + turning).multiply(speed * delta);
        Vector2 direction = getBodyDirection().add(pull);
        Vector2 newDirection = direction.normalize().multiply(CAR_SIZE.getX());
        Vector2 posDiff = direction.subtract(newDirection);
        position = position.add(posDiff);
        rotation = direction.direction();

        Vector2[] newVectors = new Vector2[3];
        if (!posDiff.equals(new Vector2(0, 0))) {
            for (int i = 0; i < triangles.length; i++) {
                for (int j = 0; j < triangles[i].getCorners().length; j++) {
                    newVectors[j] = triangles[i].getCorners()[j].add(posDiff);
                }
                triangles[i] = new TriangleImpl(newVectors);
            }
        }
    }
    protected abstract Direction getDirection();

    public Triangle[] getSolidTriangles() {
        return triangles;
    }
}
