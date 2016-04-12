package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.app.ApplicationImage;
import se.chalmers.tda367_4.geometry.Vector2;

public interface ImageEntity {
    ApplicationImage getImage();
    Vector2 getPosition();
    Vector2 getBounds();
    float getRotation();
}
