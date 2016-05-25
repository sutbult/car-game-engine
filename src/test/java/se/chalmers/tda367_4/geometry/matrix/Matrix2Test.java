package se.chalmers.tda367_4.geometry.matrix;

import org.junit.Test;
import se.chalmers.tda367_4.geometry.vector.Vector2;

import static org.junit.Assert.*;
import static se.chalmers.tda367_4.geometry.vector.VectorTestUtils.*;

public class Matrix2Test {
    @Test
    public void holdsValues() {
        float[] m = new float[2 * 2];
        for(int i = 0; i < m.length; i++) {
            m[i] = randFloat();
        }
        Matrix2 matrix = new Matrix2(m);
        for(int i = 0; i < m.length; i++) {
            assertEquals(m[i], matrix.get(i % 2, i / 2), 0);
        }
    }
    @Test
    public void defaultIdentity() {
        Matrix2 matrix = new Matrix2();
        for(int x = 0; x < 2; x++) {
            for(int y = 0; y < 2; y++) {
                float expected = (x == y) ? 1 : 0;
                assertEquals(expected, matrix.get(x, y), 0);
            }
        }
    }
    @Test
    public void scaleVector() {
        Matrix2 matrix = new Matrix2();
        matrix.set(randFloat(), 0, 0);
        matrix.set(randFloat(), 1, 1);

        Vector2 vector = new Vector2(
                randFloat(),
                randFloat()
        );
        Vector2 newVector = matrix.multiply(vector);
        assertEquals(matrix.get(0, 0) * vector.getX(), newVector.getX(), 0);
        assertEquals(matrix.get(1, 1) * vector.getY(), newVector.getY(), 0);
    }
    @Test
    public void fromRows() {
        Vector2[] rows = new Vector2[2];
        for(int i = 0; i < 2; i++) {
            rows[i] = randVector2();
        }
        Matrix2 m = Matrix2.fromRows(rows);
        for(int i = 0; i < 2; i++) {
            Vector2 v = rows[i];
            assertEquals(v.getX(), m.get(0, i), 0);
            assertEquals(v.getY(), m.get(1, i), 0);
        }
    }
    @Test
    public void fromColumns() {
        Vector2[] columns = new Vector2[2];
        for(int i = 0; i < 2; i++) {
            columns[i] = randVector2();
        }
        Matrix2 m = Matrix2.fromColumns(columns);
        for(int i = 0; i < 2; i++) {
            Vector2 v = columns[i];
            assertEquals(v.getX(), m.get(i, 0), 0);
            assertEquals(v.getY(), m.get(i, 1), 0);
        }
    }
    @Test
    public void arbitraryVector() {
        Vector2[] rows = new Vector2[2];
        for(int i = 0; i < rows.length; i++) {
            rows[i] = randVector2();
        }
        Matrix2 m = Matrix2.fromRows(rows);

        Vector2 vector = randVector2();
        Vector2 newVector = m.multiply(vector);
        assertEquals(newVector.getX(), rows[0].dot(vector), 0);
        assertEquals(newVector.getY(), rows[1].dot(vector), 0);
    }
    @Test
    public void arbitraryMatrix() {
        Vector2[][] vectors = new Vector2[2][2];
        for(int x = 0; x < 2; x++) {
            for(int y = 0; y < 2; y++) {
                vectors[x][y] = randVector2();
            }
        }
        Matrix2 first = Matrix2.fromRows(vectors[0]);
        Matrix2 second = Matrix2.fromColumns(vectors[1]);

        Matrix2 result = first.multiply(second);
        for(int x = 0; x < 2; x++) {
            for(int y = 0; y < 2; y++) {
                float expected = vectors[0][y].dot(vectors[1][x]);
                assertEquals(expected, result.get(x, y), 0);
            }
        }
    }
    @Test
    public void asAffine() {
        Vector2[] vectors = new Vector2[2];
        for(int i = 0; i < vectors.length; i++) {
            vectors[i] = randVector2();
        }
        Matrix2 matrix = Matrix2.fromRows(vectors);
        Matrix3 newMatrix = matrix.asAffine();
        for(int i = 0; i < vectors.length; i++) {
            Vector2 vector = vectors[i];
            assertEquals(vector.getX(), newMatrix.get(0, i), 0);
            assertEquals(vector.getY(), newMatrix.get(1, i), 0);
            assertEquals(0, newMatrix.get(2, i), 0);
        }
        for(int i = 0; i < 3; i++) {
            assertEquals((i == 2) ? 1 : 0, newMatrix.get(i, 2), 0);
        }
    }
    @Test
    public void equals() {
        Matrix2 first = MatrixTestUtils.randMatrix2();
        Matrix2 second = first.clone();
        assertTrue(first.equals(second));
        assertEquals(first, second);
        first.set(first.get(1, 1) * 2 + 1, 1, 1);
        assertFalse(first.equals(second));
    }
    @Test
    public void determinant() {
        Matrix2 matrix = MatrixTestUtils.randMatrix2();
        float expected =
                matrix.get(0, 0) * matrix.get(1, 1) -
                matrix.get(0, 1) * matrix.get(1, 0);
        assertEquals(expected, matrix.determinant(), 0);
    }
    @Test
    public void invertion() {
        Matrix2 matrix = MatrixTestUtils.randMatrix2();
        Matrix2 inversion = matrix.invert();
        Matrix2 result = matrix.multiply(inversion);
        Matrix2 expected = new Matrix2();
        assertTrue(expected.equals(result, 0.01f));
    }
}