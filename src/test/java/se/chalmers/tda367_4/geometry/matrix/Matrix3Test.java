package se.chalmers.tda367_4.geometry.matrix;

import org.junit.Test;
import se.chalmers.tda367_4.geometry.triangle.Triangle;
import se.chalmers.tda367_4.geometry.triangle.TriangleImpl;
import se.chalmers.tda367_4.geometry.vector.Vector2;
import se.chalmers.tda367_4.geometry.vector.Vector3;

import static org.junit.Assert.*;
import static se.chalmers.tda367_4.geometry.vector.VectorTestUtils.*;

public class Matrix3Test {
    @Test
    public void holdsValues() {
        float[] m = new float[3 * 3];
        for(int i = 0; i < m.length; i++) {
            m[i] = randFloat();
        }
        Matrix3 matrix = new Matrix3(m);
        for(int i = 0; i < m.length; i++) {
            assertEquals(m[i], matrix.get(i % 3, i / 3), 0);
        }
    }
    @Test
    public void defaultIdentity() {
        Matrix3 matrix = new Matrix3();
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                float expected = (x == y) ? 1 : 0;
                assertEquals(expected, matrix.get(x, y), 0);
            }
        }
    }
    @Test
    public void scaleVector() {
        Matrix3 matrix = new Matrix3();
        matrix.set(randFloat(), 0, 0);
        matrix.set(randFloat(), 1, 1);
        matrix.set(randFloat(), 2, 2);

        Vector3 vector = new Vector3(
                randFloat(),
                randFloat(),
                randFloat()
        );
        Vector3 newVector = matrix.multiply(vector);
        assertEquals(matrix.get(0, 0) * vector.getX(), newVector.getX(), 0);
        assertEquals(matrix.get(1, 1) * vector.getY(), newVector.getY(), 0);
        assertEquals(matrix.get(2, 2) * vector.getZ(), newVector.getZ(), 0);
    }
    @Test
    public void fromRows() {
        Vector3[] rows = new Vector3[3];
        for(int i = 0; i < 3; i++) {
            rows[i] = randVector3();
        }
        Matrix3 m = Matrix3.fromRows(rows);
        for(int i = 0; i < 3; i++) {
            Vector3 v = rows[i];
            assertEquals(v.getX(), m.get(0, i), 0);
            assertEquals(v.getY(), m.get(1, i), 0);
            assertEquals(v.getZ(), m.get(2, i), 0);
        }
    }
    @Test
    public void fromColumns() {
        Vector3[] columns = new Vector3[3];
        for(int i = 0; i < 3; i++) {
            columns[i] = randVector3();
        }
        Matrix3 m = Matrix3.fromColumns(columns);
        for(int i = 0; i < 3; i++) {
            Vector3 v = columns[i];
            assertEquals(v.getX(), m.get(i, 0), 0);
            assertEquals(v.getY(), m.get(i, 1), 0);
            assertEquals(v.getZ(), m.get(i, 2), 0);
        }
    }
    @Test
    public void arbitraryVector() {
        Vector3[] rows = new Vector3[3];
        for(int i = 0; i < rows.length; i++) {
            rows[i] = randVector3();
        }
        Matrix3 m = Matrix3.fromRows(rows);

        Vector3 vector = randVector3();
        Vector3 newVector = m.multiply(vector);
        assertEquals(newVector.getX(), rows[0].dot(vector), 0);
        assertEquals(newVector.getY(), rows[1].dot(vector), 0);
        assertEquals(newVector.getZ(), rows[2].dot(vector), 0);
    }
    @Test
    public void arbitraryMatrix() {
        Vector3[][] vectors = new Vector3[2][3];
        for(int x = 0; x < 2; x++) {
            for(int y = 0; y < 3; y++) {
                vectors[x][y] = randVector3();
            }
        }
        Matrix3 first = Matrix3.fromRows(vectors[0]);
        Matrix3 second = Matrix3.fromColumns(vectors[1]);

        Matrix3 result = first.multiply(second);
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                float expected = vectors[0][y].dot(vectors[1][x]);
                assertEquals(expected, result.get(x, y), 0);
            }
        }
    }
    @Test
    public void createTranslation() {
        Vector2 vector = randVector2();
        Matrix3 matrix = Matrix3.createTranslation(vector);
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                float expected;
                if(x == 2 && y == 0) {
                    expected = vector.getX();
                }
                else if(x == 2 && y == 1) {
                    expected = vector.getY();
                }
                else {
                    expected = (x == y) ? 1 : 0;
                }
                assertEquals(expected, matrix.get(x, y), 0);
            }
        }
    }
}