package se.chalmers.tda367_4.geometry.triangle;

import se.chalmers.tda367_4.geometry.vector.Vector2;

public interface Triangle {
    Vector2[] getCorners();
    boolean intersects(Triangle other);
}
