package se.chalmers.tda367_4.geometry;

public class Vector3 extends Vector2 {
    private final float z;

    public Vector3(float x, float y, float z) {
        super(x, y);
        this.z = z;
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
}
