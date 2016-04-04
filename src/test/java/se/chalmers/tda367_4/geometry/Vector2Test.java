package se.chalmers.tda367_4.geometry;

import org.junit.Test;

import static org.junit.Assert.*;
import static se.chalmers.tda367_4.utils.TestUtils.*;

public class Vector2Test {
    @Test
    public void getCorrect() throws Exception {
        float x = randFloat();
        float y = randFloat();
        Vector2 vector = new Vector2(x, y);
        assertEquals(x, vector.getX(), 0);
        assertEquals(y, vector.getY(), 0);
    }
}