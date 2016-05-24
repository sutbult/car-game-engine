package se.chalmers.tda367_4.geometry.vector;

public class VectorTestUtils {
    public static float randFloat() {
        float base = (float)Math.random();
        float pow = (int)(Math.random() * 20) - 10;
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
