package se.chalmers.tda367_4.geometry;

import org.junit.Test;

import static org.junit.Assert.*;
import static se.chalmers.tda367_4.geometry.GeometryTestUtils.*;

public class Vector3Test {
    @Test
    public void holdsValues() throws Exception {
        float x = randFloat();
        float y = randFloat();
        float z = randFloat();
        Vector3 vector = new Vector3(x, y, z);
        assertEquals(x, vector.getX(), 0);
        assertEquals(y, vector.getY(), 0);
        assertEquals(z, vector.getZ(), 0);
    }
    @Test
    public void scales() throws Exception {
        float s = randFloat();
        Vector3 vector = randVector3();
        Vector3 newVector = vector.multiply(s);
        assertEquals(vector.getX() * s, newVector.getX(), 0);
        assertEquals(vector.getY() * s, newVector.getY(), 0);
        assertEquals(vector.getZ() * s, newVector.getZ(), 0);
    }
    @Test
    public void dot() {
        Vector3 first = randVector3();
        Vector3 second = randVector3();
        float expected =
                first.getX() * second.getX() +
                        first.getY() * second.getY() +
                        first.getZ() * second.getZ();
        assertEquals(expected, first.dot(second), 0);
    }
    @Test
    public void equality() {
        Vector3 first = randVector3();
        Vector3 second = new Vector3(first);
        assertFalse(first == second);
        assertTrue(first.equals(second));
        assertTrue(second.equals(first));
    }
    @Test
    public void subtract() {
        Vector3 first = randVector3();
        Vector3 second = randVector3();
        Vector3 result = first.subtract(second);
        assertEquals(first.getX() - second.getX(), result.getX(), 0);
        assertEquals(first.getY() - second.getY(), result.getY(), 0);
        assertEquals(first.getZ() - second.getZ(), result.getZ(), 0);
    }
}