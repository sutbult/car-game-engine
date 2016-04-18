package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.geometry.*;

public class Environment implements SolidEntity, SingleColorEntity {

    private Triangle triangle;
    private GraphicalTriangle graphicalTriangle;

    public Environment (Vector2 a, Vector2 b, Vector2 c, float red, float green, float blue) {
        this(new TriangleImpl(a, b, c), red, green, blue);

    }

    public Environment (Triangle triangle, float red, float green, float blue) {
        this.triangle = triangle;
        graphicalTriangle = new GraphicalTriangleImpl(triangle.getCorners(), red, green, blue);
    }

    public Triangle[] getSolidTriangles() {
        return new Triangle[] {triangle};
    }

    public GraphicalTriangle getGraphicalTriangle() {
        return graphicalTriangle;
    }
}
