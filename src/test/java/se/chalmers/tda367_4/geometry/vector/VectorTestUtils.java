package se.chalmers.tda367_4.geometry.vector;

public class VectorTestUtils {
    private final static int RANGE = 6;

    public static float randFloat() {
        float base = (float)Math.random() * 2 - 1;
        float pow = (float)(Math.random() - 0.5f) * RANGE;
        return base * (float)Math.pow(10, pow);
    }
    public static Vector2 randVector2() {
        return new Vector2(
                randFloat(),
                randFloat()
        );
    }
    public static Vector3 randVector3() {
        return new Vector3(
                randVector2(),
                randFloat()
        );
    }
}
