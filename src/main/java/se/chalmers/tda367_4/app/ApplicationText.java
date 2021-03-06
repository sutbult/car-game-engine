package se.chalmers.tda367_4.app;

import se.chalmers.tda367_4.geometry.color.ApplicationColor;
import se.chalmers.tda367_4.geometry.vector.Vector2;

public interface ApplicationText {
    String getText();
    String getFont();
    Vector2 getPosition();
    float getHeight();
    boolean isBold();
    ApplicationColor getColor();
}
