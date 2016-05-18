package se.chalmers.tda367_4.geometry;

public class GraphicalTriangleImpl extends TriangleImpl implements GraphicalTriangle {
    private ApplicationColor color;

    public GraphicalTriangleImpl(Vector2 a, Vector2 b, Vector2 c, ApplicationColor color) {
        super(a, b, c);
        this.color = color;
    }
    public GraphicalTriangleImpl(Vector2[] corners, ApplicationColor color) {
        this(corners[0], corners[1], corners[2], color);
    }
    public ApplicationColor getColor() {
        return this.color;
    }
}
