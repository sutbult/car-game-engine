package se.chalmers.tda367_4.geometry;

public class Vector2 {
    private final float x;
    private final float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Vector2(Vector2 v) {
        this(v.getX(), v.getY());
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public String toString() {
        return x + " " + y;
    }
    public Vector2 multiply(float s) {
        return new Vector2(
                x * s,
                y * s
        );
    }
}
