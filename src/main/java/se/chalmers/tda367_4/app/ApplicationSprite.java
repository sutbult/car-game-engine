package se.chalmers.tda367_4.app;

import se.chalmers.tda367_4.geometry.vector.Vector2;

public interface ApplicationSprite {
    ApplicationImage getImage();
    Vector2 getPosition();
    Vector2 getBounds();
    float getRotation();
}
