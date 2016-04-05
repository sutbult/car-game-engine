package se.chalmers.tda367_4.geometry;

public class Vector3 extends Vector2 {
    private final float z;

    public Vector3(float x, float y, float z) {
        super(x, y);
        this.z = z;
    }
    public Vector3(Vector3 v) {
        this(v.getX(), v.getY(), v.z);
    }
    public Vector3(Vector2 v, float z) {
        this(v.getX(), v.getY(), z);
    }
    public final float getZ() {
        return z;
    }
    public String toString() {
        return super.toString() + " " + z;
    }
    public Vector3 multiply(float s) {
        return new Vector3(
                super.multiply(s),
                z * s
        );
    }
    public float dot(Vector3 other) {
        return super.dot(other) + this.z * other.z;
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
            Vector3 o = (Vector3)other;
            return
                    getX() == o.getX() &&
                    getY() == o.getY() &&
                    z == o.z;
        }
    }
    public Vector3 subtract(Vector3 other) {
        return new Vector3(
                super.subtract(other),
                z - other.z
        );
    }
}
