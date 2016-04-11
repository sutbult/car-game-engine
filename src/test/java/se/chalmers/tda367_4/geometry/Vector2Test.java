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
    @Test
    public void dot() {
        Vector2 first = randVector2();
        Vector2 second = randVector2();
        float expected =
                first.getX() * second.getX() +
                first.getY() * second.getY();
        assertEquals(expected, first.dot(second), 0);
    }
    @Test
    public void equality() {
        Vector2 first = randVector2();
        Vector2 second = new Vector2(first);
        assertFalse(first == second);
        assertTrue(first.equals(second));
        assertTrue(second.equals(first));
    }
    @Test
    public void asAffine() {
        Vector2 vector = randVector2();
        Vector3 newVector = vector.asAffine();
        assertEquals(vector.getX(), newVector.getX(), 0);
        assertEquals(vector.getY(), newVector.getY(), 0);
        assertEquals(1, newVector.getZ(), 0);
    }
    @Test
    public void subtract() {
        Vector2 first = randVector2();
        Vector2 second = randVector2();
        Vector2 result = first.subtract(second);
        assertEquals(first.getX() - second.getX(), result.getX(), 0);
        assertEquals(first.getY() - second.getY(), result.getY(), 0);
    }
    @Test
    public void add() {
        Vector2 first = randVector2();
        Vector2 second = randVector2();
        Vector2 result = first.add(second);
        assertEquals(first.getX() + second.getX(), result.getX(), 0);
        assertEquals(first.getY() + second.getY(), result.getY(), 0);
    }
    @Test
    public void length() {
        Vector2 vector = randVector2();
        float expected = (float)Math.sqrt(
                Math.pow(vector.getX(), 2) +
                Math.pow(vector.getY(), 2)
        );
        assertEquals(expected, vector.length(), 0);
    }
    @Test
    public void egyptianTriangle() {
        Vector2 vector = new Vector2(3, 4);
        assertEquals(5, vector.length(), 0);
    }
    @Test
    public void normalize() {
        Vector2 vector = randVector2();
        vector = vector.normalize();
        assertEquals(1, vector.length(), 0.01f);
    }
}