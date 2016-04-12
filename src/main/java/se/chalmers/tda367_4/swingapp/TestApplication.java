package se.chalmers.tda367_4.swingapp;

import se.chalmers.tda367_4.app.Application;
import se.chalmers.tda367_4.app.ApplicationCamera;
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
        env.getGraphics().setCamera(new TestCamera());
        env.getGraphics().renderImage(image, 30 + (float)(Math.sin(rot) * 20), 10, 10, 10, rot * 2);
        env.getGraphics().renderTriangle(new GraphicalTriangleImpl(
                new Vector2(15, 10),
                new Vector2(10, 20),
                new Vector2(30, 20),
                0.5f, 1, 0
        ));
    }
    private class TestCamera implements ApplicationCamera {
        public Vector2 getPosition() {
            return new Vector2(50, 20);
        }
        public float getHeight() {
            return 100;
        }
    }
}
