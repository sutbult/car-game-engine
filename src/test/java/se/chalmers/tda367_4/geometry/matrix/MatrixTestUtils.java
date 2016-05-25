package se.chalmers.tda367_4.geometry.matrix;

import se.chalmers.tda367_4.geometry.vector.Vector2;

import static se.chalmers.tda367_4.geometry.vector.VectorTestUtils.randVector2;

public class MatrixTestUtils {
    public static Matrix2 randMatrix2() {
        Vector2[] vectors = new Vector2[2];
        for(int i = 0; i < vectors.length; i++) {
            vectors[i] = randVector2();
        }
        return Matrix2.fromRows(vectors);
    }
}
