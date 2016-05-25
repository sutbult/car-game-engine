package se.chalmers.tda367_4.game.entities;

import se.chalmers.tda367_4.game.entities.SolidEntity;
import se.chalmers.tda367_4.geometry.triangle.Triangle;

public class Utilities {

    public static boolean entityCollides(SolidEntity first, SolidEntity second) {
        Triangle[] firstTriangles = first.getSolidTriangles();
        Triangle[] secondTriangles = second.getSolidTriangles();

        for (Triangle firstTriangle: firstTriangles) {
            for (Triangle secondTriangle: secondTriangles) {
                if (firstTriangle.intersects(secondTriangle)) {
                    return true;
                }
            }
        }
        return false;
    }
}
