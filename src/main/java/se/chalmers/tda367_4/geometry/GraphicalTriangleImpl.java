package se.chalmers.tda367_4.geometry;

public class GraphicalTriangleImpl extends TriangleImpl implements GraphicalTriangle {
    private float r;
    private float g;
    private float b;

    public GraphicalTriangleImpl(Vector2 a, Vector2 b, Vector2 c, float red, float green, float blue) {
        super(a, b, c);
        this.r = red;
        this.g = green;
        this.b = blue;
    }
    public GraphicalTriangleImpl(Vector2[] corners, float r, float g, float b) {
        this(corners[0], corners[1], corners[2], r, g, b);
    }
    public float getR() {
        return r;
    }
    public float getG() {
        return g;
    }
    public float getB() {
        return b;
    }
}
