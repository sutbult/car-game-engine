package se.chalmers.tda367_4.swingapp;

import se.chalmers.tda367_4.app.Application;
import se.chalmers.tda367_4.geometry.color.ApplicationColor;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.geometry.triangle.GraphicalTriangle;
import se.chalmers.tda367_4.geometry.triangle.GraphicalTriangleImpl;
import se.chalmers.tda367_4.geometry.triangle.Triangle;
import se.chalmers.tda367_4.geometry.triangle.TriangleImpl;
import se.chalmers.tda367_4.geometry.vector.Vector2;

import java.util.ArrayList;

public class TriangleTestApplication implements Application {
    private static float X_DIST = 150;
    private static float Y_DIST = 150;

    public static void main(String[] args) {
        new SwingApplication(new TriangleTestApplication());
    }
    private ApplicationEnvironment env;
    private TriangleTestApplication() {
        xOff = 0;
        yOff = 0;
        angOff = 0;
        mainTriangle = new GraphicalTriangleImpl(
                new Vector2(200, 100),
                new Vector2(300, 450),
                new Vector2(700, 400),
                new ApplicationColor(255,255,0)
        );
    }
    public void init(ApplicationEnvironment appEnv) {
        env = appEnv;
    }
    public void update(float delta) {
        xOff += delta * 100;
        yOff += delta * 75;
        angOff += delta * Math.PI / 2;

        if(xOff > X_DIST) xOff -= X_DIST;
        if(yOff > Y_DIST) yOff -= Y_DIST;
        if(angOff > Math.PI * 2) angOff -= Math.PI * 2;

        updateTriangles();
        updateGraphicalTriangles();
    }
    public void render() {
        env.getGraphics().renderTriangle(mainTriangle);
        for(GraphicalTriangle trig : graphicalTriangles) {
            env.getGraphics().renderTriangle(trig);
        }
    }
    private GraphicalTriangle mainTriangle;
    private ArrayList<Triangle> triangles;
    private ArrayList<GraphicalTriangle> graphicalTriangles;
    private float xOff;
    private float yOff;
    private float angOff;
    private void updateTriangles() {
        triangles = new ArrayList<Triangle>();
        for(int xi = -1; xi < 10; xi++) {
            for(int yi = -1; yi < 10; yi++) {
                Vector2 off = new Vector2(
                        xi * X_DIST + xOff,
                        yi * Y_DIST + yOff
                );
                Vector2[] corners = new Vector2[3];
                for(int i = 0; i < 3; i++) {
                    float ang = (float)Math.PI * 2 * i / 3;
                    ang += angOff;
                    corners[i] = new Vector2(
                            (float)Math.cos(ang) * 50,
                            (float)Math.sin(ang) * 50
                    ).add(off);
                }
                triangles.add(new TriangleImpl(corners));
            }
        }
    }
    private void updateGraphicalTriangles() {
        graphicalTriangles = new ArrayList<GraphicalTriangle>();
        for(Triangle triangle : triangles) {
            int r;
            int g;
            int b;
            if(triangle.intersects(mainTriangle)) {
                r = 255;
                g = 0;
                b = 0;
            }
            else {
                r = 0;
                g = 255;
                b = 0;
            }
            graphicalTriangles.add(new GraphicalTriangleImpl(
                    triangle.getCorners(), new ApplicationColor(r,g,b)
            ));
        }
    }
}
