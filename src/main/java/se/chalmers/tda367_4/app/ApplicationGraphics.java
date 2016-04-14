package se.chalmers.tda367_4.app;

import se.chalmers.tda367_4.geometry.GraphicalTriangle;

public interface ApplicationGraphics {
    void setCamera(ApplicationCamera camera);
    void renderImage(ApplicationSprite sprite);
    void renderTriangle(GraphicalTriangle triangle);
    ApplicationImage loadImage(String src);
}
