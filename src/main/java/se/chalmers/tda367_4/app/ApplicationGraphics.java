package se.chalmers.tda367_4.app;

import se.chalmers.tda367_4.geometry.GraphicalTriangle;

public interface ApplicationGraphics {
    void renderImage(ApplicationImage image, int x, int y, int w, int h, float r);
    void renderTriangle(GraphicalTriangle triangle);
    ApplicationImage loadImage(String src);
}
