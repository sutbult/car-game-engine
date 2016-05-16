package se.chalmers.tda367_4.swingapp;

import se.chalmers.tda367_4.app.*;
import se.chalmers.tda367_4.game.GameText;
import se.chalmers.tda367_4.geometry.GraphicalTriangleImpl;
import se.chalmers.tda367_4.geometry.Vector2;

public class TestApplication implements Application {
    public static void main(String[] args) {
        new SwingApplication(new TestApplication());
    }
    private TestApplication() {}

    private ApplicationEnvironment env;
    private ApplicationImage image;
    private Cube cube;
    public void init(ApplicationEnvironment appEnv) {
        env = appEnv;
        image = new ApplicationImage("car_yellow.png");
        cube = new Cube();
    }

    public void update(float delta) {
        //cube.update(delta);
        cube.update(0);
    }

    public void render() {
        env.getGraphics().setCamera(new TestCamera());
        env.getGraphics().renderImage(cube);
        env.getGraphics().renderTriangle(new GraphicalTriangleImpl(
                new Vector2(15, 10),
                new Vector2(10, 20),
                new Vector2(30, 20),
                0.5f, 1, 0
        ));

        env.getGraphics().renderText(new GameText("Example", "sans-Serif", cube.getPosition(), 20, false));
    }

    private class Cube implements ApplicationSprite {
        public Vector2 position;
        public float rotation = 0;

        public void update(float delta) {
            rotation += delta;
            position = new Vector2(30 + (float)(Math.sin(rotation) * 20), 10);
        }
        public ApplicationImage getImage() {
            return image;
        }
        public Vector2 getPosition() {
            return position;
        }
        public Vector2 getBounds() {
            return new Vector2(10, 10);
        }
        public float getRotation() {
            return rotation * 2;
        }
    }
    private class TestCamera implements ApplicationCamera {
        public Vector2 getPosition() {
            return cube.getPosition();
            //return new Vector2(50, 20);
        }
        public float getHeight() {
            return 100;
        }
    }
}