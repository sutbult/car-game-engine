package se.chalmers.tda367_4.geometry.color;

final public class ApplicationColor {

    // Values must be between 0 and 255.
    final private int red;
    final private int green;
    final private int blue;

    private void check(int red,
                       int green,
                       int blue) {
        if (red < 0 || red > 255
                || green < 0 || green > 255
                || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public ApplicationColor(int red, int green, int blue) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getR() {
        return this.red;
    }

    public int getG() {
        return this.green;
    }

    public int getB() {
        return this.blue;
    }
}
