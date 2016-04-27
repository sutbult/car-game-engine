package se.chalmers.tda367_4.app;

final public class ApplicationColor {
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

    public ApplicationColor(int red,
                        int green,
                        int blue) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public float[] getColor() {
        float red = this.red;
        float green = this.green;
        float blue = this.blue;
        float[] color = {red, green, blue};
        return color;
    }
}
