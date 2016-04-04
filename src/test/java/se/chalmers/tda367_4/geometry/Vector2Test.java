package se.chalmers.tda367_4.geometry;

import org.junit.Test;

import static org.junit.Assert.*;
import static se.chalmers.tda367_4.utils.TestUtils.*;

public class Vector2Test {
    @Test
    public void holdsValue() throws Exception {
        float x = randFloat();
        float y = randFloat();
        Vector2 vector = new Vector2(x, y);
        assertEquals(x, vector.getX(), 0);
        assertEquals(y, vector.getY(), 0);
    }
    @Test
    public void scales() throws Exception {
        float s = randFloat();
        Vector2 vector = randVector2();
        Vector2 newVector = vector.multiply(s);
        assertEquals(vector.getX() * s, newVector.getX(), 0);
        assertEquals(vector.getY() * s, newVector.getY(), 0);
    }
}