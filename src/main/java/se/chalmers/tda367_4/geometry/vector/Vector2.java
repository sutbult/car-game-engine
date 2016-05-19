package se.chalmers.tda367_4.geometry.vector;

public class Vector2 {
    private final float x;
    private final float y;

    public static Vector2 fromAngle(float angle) {
        return new Vector2(
                (float)Math.cos(angle),
                (float)Math.sin(angle)
        );
    }

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
    public float dot(Vector2 other) {
        return this.x * other.x + this.y * other.y;
    }
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }
        else if(other == null) {
            return false;
        }
        else if(this.getClass() != other.getClass()) {
            return false;
        }
        else {
            Vector2 o = (Vector2)other;
            return x == o.x && y == o.y;
        }
    }
    public Vector3 asAffine() {
        return new Vector3(this, 1);
    }
    public Vector2 negate() {
        return new Vector2(-x, -y);
    }
    public Vector2 subtract(Vector2 other) {
        return new Vector2(
                x - other.x,
                y - other.y
        );
    }
    public Vector2 add(Vector2 other) {
        return new Vector2(
                x + other.x,
                y + other.y
        );
    }
    public float length() {
        return (float)Math.sqrt(x * x + y * y);
    }
    public Vector2 normalize() {
        return multiply(1 / length());
    }
    public float direction() {
        return (float)Math.atan2(y, x);
    }
}
