package se.chalmers.tda367_4.geometry;

import org.junit.Test;

import static org.junit.Assert.*;
import static se.chalmers.tda367_4.utils.TestUtils.*;

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
}