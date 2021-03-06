package se.chalmers.tda367_4.app;

import se.chalmers.tda367_4.geometry.color.ApplicationColor;
import se.chalmers.tda367_4.geometry.triangle.GraphicalTriangle;

public interface ApplicationGraphics {
    void setBackgroundColor(ApplicationColor color);
    void setCamera(ApplicationCamera camera);
    void renderImage(ApplicationSprite sprite);
    void renderTriangle(GraphicalTriangle triangle);
    void renderText(ApplicationText text);
}
