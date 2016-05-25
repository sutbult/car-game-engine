package se.chalmers.tda367_4.game.entities.power_ups;

import se.chalmers.tda367_4.app.ApplicationImage;
import se.chalmers.tda367_4.game.entities.ImageEntity;
import se.chalmers.tda367_4.game.entities.SolidEntity;
import se.chalmers.tda367_4.geometry.triangle.Triangle;
import se.chalmers.tda367_4.geometry.triangle.TriangleImpl;
import se.chalmers.tda367_4.geometry.vector.Vector2;

public abstract class PowerUp implements ImageEntity, SolidEntity {

    private Vector2 position;
    private ApplicationImage image;
    private final static Vector2 POWERUP_SIZE = new Vector2(1, 1);
    private Triangle[] triangles;

    public PowerUp(Vector2 position, String imageName) {
        this.position = position;
        image = new ApplicationImage(imageName);
        triangles = new Triangle[2];
        triangles[0] = new TriangleImpl(
                new Vector2(position.getX() + POWERUP_SIZE.getX() / 2, position.getY() + POWERUP_SIZE.getY() / 2),
                new Vector2(position.getX() - POWERUP_SIZE.getX() / 2, position.getY() + POWERUP_SIZE.getY() / 2),
                new Vector2(position.getX() - POWERUP_SIZE.getX() / 2, position.getY() - POWERUP_SIZE.getY() / 2)
        );
        triangles[1] = new TriangleImpl(
                new Vector2(position.getX() + POWERUP_SIZE.getX() / 2, position.getY() + POWERUP_SIZE.getY() / 2),
                new Vector2(position.getX() + POWERUP_SIZE.getX() / 2, position.getY() - POWERUP_SIZE.getY() / 2),
                new Vector2(position.getX() - POWERUP_SIZE.getX() / 2, position.getY() - POWERUP_SIZE.getY() / 2)
        );
    }

    public ApplicationImage getImage() {
        return image;
    }

    public Vector2 getBounds() {
        return POWERUP_SIZE;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getRotation() {
        return 0;
    }

    public Triangle[] getSolidTriangles() {
        return triangles;
    }

    public abstract float getMultiplier();

    public abstract int getDuration();
}
