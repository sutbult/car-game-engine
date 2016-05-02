package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationImage;
import se.chalmers.tda367_4.app.ApplicationSprite;
import se.chalmers.tda367_4.game.Direction;
import se.chalmers.tda367_4.geometry.*;

public abstract class Car implements ImageEntity, SolidEntity {
    private final static float SPEED = 5;
    private final static float TURN_RATE = -0.5f;
    private final static Vector2 CAR_SIZE = new Vector2(2, 1);
    private ApplicationImage image;
    private Vector2 position;
    private Vector2 prevPosition;
    private float rotation;
    private float prevRotation;
    private Triangle[] triangles;
    private Triangle[] prevTriangles;

    public Car(ApplicationEnvironment env) {
        image = env.getGraphics().loadImage("car_yellow.png");
        position = new Vector2(0.1f, 0.1f);
        rotation = 0;
        updateTriangles();
        prevPosition = position;
        prevTriangles = triangles;
        prevRotation = rotation;
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

    public void revert() {
        position = prevPosition;
        triangles = prevTriangles;
        rotation = prevRotation;
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

        if (!prevPosition.equals(position)) {
            prevPosition = position;
            prevTriangles = triangles;
            prevRotation = rotation;
        }

        position = position.add(posDiff);
        rotation = direction.direction();
        updateTriangles();
    }

    private void updateTriangles() {
        Matrix2 matrix = new Matrix2(new float[] { (float) Math.cos(rotation), (float) -Math.sin(rotation),
            (float) Math.sin(rotation), (float) Math.cos(rotation)});

        Vector2 topLeft = matrix.multiply(new Vector2(-CAR_SIZE.getX() / 2, CAR_SIZE.getY() / 2));
        Vector2 topRight = matrix.multiply(new Vector2(CAR_SIZE.getX() / 2, CAR_SIZE.getY() / 2));
        Vector2 bottomLeft = matrix.multiply(new Vector2(-CAR_SIZE.getX() / 2, -CAR_SIZE.getY() / 2));
        Vector2 bottomRight = matrix.multiply(new Vector2(CAR_SIZE.getX() / 2, -CAR_SIZE.getY() / 2));

        Vector2 lowerRight = new Vector2(position.getX() + bottomRight.getX(), position.getY() + bottomRight.getY());
        Vector2 lowerLeft = new Vector2(position.getX() + bottomLeft.getX(), position.getY() + bottomLeft.getY());
        Vector2 upperRight = new Vector2(position.getX() + topRight.getX(), position.getY() + topRight.getY());
        Vector2 upperLeft = new Vector2(position.getX() + topLeft.getX(), position.getY() + topLeft.getY());

        this.triangles = new Triangle[] {new TriangleImpl(upperLeft, upperRight, lowerLeft),
                new TriangleImpl(lowerLeft, upperRight, lowerRight)};
    }

    protected abstract Direction getDirection();

    public Triangle[] getSolidTriangles() {
        return triangles;
    }
}
