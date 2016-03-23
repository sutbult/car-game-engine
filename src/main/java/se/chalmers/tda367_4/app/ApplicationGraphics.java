package se.chalmers.tda367_4.app;

public interface ApplicationGraphics {
    void renderImage(ApplicationImage image, int x, int y, int w, int h, float r);
    ApplicationImage loadImage(String src);
}
