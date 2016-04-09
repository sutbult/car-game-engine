package se.chalmers.tda367_4.geometry;

/**
 * Created by samuel on 2016-04-09.
 */
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
