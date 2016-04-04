package se.chalmers.tda367_4.geometry;

public interface Triangle {
    Vector2[] getCorners();
    boolean intersects(Triangle other);
}
