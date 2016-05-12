package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.app.ApplicationCamera;
import se.chalmers.tda367_4.app.ApplicationColor;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.game.entities.Car;
import se.chalmers.tda367_4.game.entities.Player;
import se.chalmers.tda367_4.game.entities.Police;
import se.chalmers.tda367_4.scenes.Scene;
import se.chalmers.tda367_4.geometry.Vector2;

import se.chalmers.tda367_4.game.entities.*;
import se.chalmers.tda367_4.geometry.*;

import java.util.ArrayList;
import java.util.List;

public class GameApplication implements Scene {
    private ApplicationEnvironment appEnv;
    private Car car;
    private Police police;
    private Environment environment;
    private ApplicationColor color;

    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
        appEnv.getGraphics().setCamera(new GameCamera());
        car = new Player(appEnv);
        police = new Police(appEnv, car);
        color = new ApplicationColor(5,0,9);

        GraphicalTriangle triangle = new GraphicalTriangleImpl(new Vector2(4, 4), new Vector2(4, 2), new Vector2(0, 4),
                color);
        GraphicalTriangle triangle2 = new GraphicalTriangleImpl(new Vector2(4, 4), new Vector2(2, 2), new Vector2(2, 4),
                color);
        GraphicalTriangle triangle3 = new GraphicalTriangleImpl(new Vector2(-3, -3), new Vector2(-1, -2), new Vector2(0, 0),
                color);
        List<GraphicalTriangle> list = new ArrayList<GraphicalTriangle>();
        list.add(triangle);
        list.add(triangle2);
        List<GraphicalTriangle> list2 = new ArrayList<GraphicalTriangle>();
        list2.add(triangle3);

        environment = new Environment(list, list2);
    }
    public void update(float delta) {
        car.move(delta);
        police.move(delta);

        if (entityCollides(car, environment)) {
            car.revert();
        }
    }
    public void render() {
        for (GraphicalTriangle triangle : environment.getGraphicalTriangles()) {
            appEnv.getGraphics().renderTriangle(triangle);
        }

        appEnv.getGraphics().renderImage(car);
        appEnv.getGraphics().renderImage(police);
        appEnv.getGraphics().renderText(
                new GameText("Example", "Serif", new Vector2(1, 1), 1, false,
                new ApplicationColor(113,13,31)));
    }
    public Scene newScene() {
        return null;
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
    private class GameCamera implements ApplicationCamera {
        public Vector2 getPosition() {
            // What is supposed to be returned when the environment
            // has been added:
            return car.getPosition();
            //return new Vector2(0, 0);
        }
        public float getHeight() {
            return 10;
        }
    }
}
