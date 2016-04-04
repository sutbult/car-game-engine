package se.chalmers.tda367_4.utils;

public class TestUtils {
    public static float randFloat() {
        //return Math.random()
        float base = (float)Math.random();
        float pow = (int)(Math.random() * 20) - 10;
        return base * (float)Math.pow(10, pow);
    }
}
