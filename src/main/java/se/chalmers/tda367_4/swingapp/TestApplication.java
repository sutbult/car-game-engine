package se.chalmers.tda367_4.swingapp;

import se.chalmers.tda367_4.app.Application;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationImage;
import se.chalmers.tda367_4.geometry.GraphicalTriangleImpl;
import se.chalmers.tda367_4.geometry.Vector2;

public class TestApplication implements Application {
    public static void main(String[] args) {
        new SwingApplication(new TestApplication());
    }
    private TestApplication() {}

    private ApplicationEnvironment env;
    private ApplicationImage image;
    public void init(ApplicationEnvironment appEnv) {
        env = appEnv;
        image = env.getGraphics().loadImage("orange");
    }

    private float rot = 0;
    public void update(float delta) {
        rot += delta;
    }

    public void render() {
        env.getGraphics().renderImage(image, 300 + (int)(Math.sin(rot) * 200), 100, 100, 100, rot * 2);
        env.getGraphics().renderTriangle(new GraphicalTriangleImpl(
                new Vector2(150, 100),
                new Vector2(100, 200),
                new Vector2(300, 200),
                0.5f, 1, 0
        ));
    }
}
