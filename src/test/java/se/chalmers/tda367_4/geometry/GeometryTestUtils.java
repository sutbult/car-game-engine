package se.chalmers.tda367_4.geometry;

import se.chalmers.tda367_4.geometry.Matrix2;
import se.chalmers.tda367_4.geometry.Vector2;
import se.chalmers.tda367_4.geometry.Vector3;

public class GeometryTestUtils {
    public static float randFloat() {
        //return Math.random()
        float base = (float)Math.random();
        float pow = (int)(Math.random() * 20) - 10;
        return base * (float)Math.pow(10, pow);
    }
    public static Vector2 randVector2() {
        return new Vector2(
                randFloat(),
                randFloat()
        );
    }
    public static Vector3 randVector3() {
        return new Vector3(
                randVector2(),
                randFloat()
        );
    }
    public static Matrix2 randMatrix2() {
        Vector2[] vectors = new Vector2[2];
        for(int i = 0; i < vectors.length; i++) {
            vectors[i] = randVector2();
        }
        return Matrix2.fromRows(vectors);
    }
}
