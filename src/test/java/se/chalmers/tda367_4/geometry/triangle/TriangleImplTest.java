package se.chalmers.tda367_4.geometry.triangle;

import org.junit.Test;
import se.chalmers.tda367_4.geometry.vector.Vector2;

import static org.junit.Assert.*;
import static se.chalmers.tda367_4.geometry.vector.VectorTestUtils.randFloat;

public class TriangleImplTest {
    @Test
    public void getCorners() throws Exception {
        Vector2[] corners = new Vector2[3];
        for(int i = 0; i < corners.length; i++) {
            corners[i] = new Vector2(randFloat(), randFloat());
        }

        Triangle triangle = new TriangleImpl(corners);
        assertArrayEquals(corners, triangle.getCorners());
    }
    @Test
    public void intersects() throws Exception {
        Triangle first = new TriangleImpl(
                new Vector2(0, 0),
                new Vector2(0, 3),
                new Vector2(3, 0)
        );
        Triangle second = new TriangleImpl(
                new Vector2(1, 1),
                new Vector2(4, 1),
                new Vector2(1, 4)
        );
        assertTrue(first.intersects(second));
    }
    @Test
    public void intersectsNot() throws Exception {
        Triangle first = new TriangleImpl(
                new Vector2(2, 2),
                new Vector2(2, 5),
                new Vector2(5, 2)
        );
        Triangle second = new TriangleImpl(
                new Vector2(6, 3),
                new Vector2(6, 2),
                new Vector2(7, 2)
        );
        assertFalse(first.intersects(second));
    }
}