package se.chalmers.tda367_4.geometry;

public class TriangleImpl implements Triangle {

    private Vector2[] corners;

    public TriangleImpl(Vector2 a, Vector2 b, Vector2 c) {
        corners = new Vector2[3];
        corners[0] = a;
        corners[1] = b;
        corners[2] = c;
    }
    public TriangleImpl(Vector2[] corners) {
        this(corners[0], corners[1], corners[2]);
    }

    public Vector2[] getCorners() {
        return corners.clone();
    }

    public boolean intersects(Triangle other) {
        if(!(other instanceof Triangle)) {
            other = new TriangleImpl(other.getCorners());
        }

        return false;
    }
}
