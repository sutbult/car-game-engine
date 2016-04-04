package se.chalmers.tda367_4.geometry;

import org.junit.Test;

import static org.junit.Assert.*;
import static se.chalmers.tda367_4.utils.TestUtils.randFloat;

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
                new Vector2(0, 3),
                new Vector2(0, 0),
                new Vector2(3, 0)
        );
        Triangle second = new TriangleImpl(
                new Vector2(1, 1),
                new Vector2(2, 1),
                new Vector2(1, 2)
        );
        //assertTrue(first.intersects(second));
    }
    @Test
    public void intersectsNot() throws Exception {
        Triangle first = new TriangleImpl(
                new Vector2(0, 3),
                new Vector2(0, 0),
                new Vector2(3, 0)
        );
        Triangle second = new TriangleImpl(
                new Vector2(4, 1),
                new Vector2(4, 0),
                new Vector2(5, 0)
        );
        assertFalse(first.intersects(second));
    }
}