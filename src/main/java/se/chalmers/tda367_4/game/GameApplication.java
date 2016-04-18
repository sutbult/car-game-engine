package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.app.Application;
import se.chalmers.tda367_4.app.ApplicationCamera;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.game.entities.*;
import se.chalmers.tda367_4.geometry.Triangle;
import se.chalmers.tda367_4.geometry.TriangleImpl;
import se.chalmers.tda367_4.geometry.Vector2;
import se.chalmers.tda367_4.swingapp.SwingApplication;

public class GameApplication implements Application {
    public static void main(String[] args) {
        new SwingApplication(new GameApplication());
    }
    private ApplicationEnvironment appEnv;
    private Car car;
    private Environment obstacle;
    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
        appEnv.getGraphics().setCamera(new GameCamera());
        car = new Player(appEnv);

        Triangle triangle = new TriangleImpl(new Vector2(4, 4), new Vector2(4, 2), new Vector2(0, 4));
        obstacle = new Obstacle(triangle, 0.1f, 0.3f, 0.5f);
    }

    public void update(float delta) {
        car.move(delta);

        if (entityCollides(car, obstacle)) {
            car.revert();
        }
    }

    private boolean entityCollides(SolidEntity first, SolidEntity second) {
        Triangle[] carTriangles = first.getSolidTriangles();
        Triangle[] obstacleTriangles = second.getSolidTriangles();

        for (Triangle carTriangle: carTriangles) {
            for (Triangle obstacleTriangle: obstacleTriangles) {
                if (carTriangle.intersects(obstacleTriangle)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void render() {
        appEnv.getGraphics().renderImage(car);
        appEnv.getGraphics().renderTriangle(obstacle.getGraphicalTriangle());
    }
    private class GameCamera implements ApplicationCamera {
        public Vector2 getPosition() {
            // What is supposed to be returned when the environment
            // has been added:
            //return car.getPosition();
            return new Vector2(0, 0);
        }
        public float getHeight() {
            return 10;
        }
    }
}
