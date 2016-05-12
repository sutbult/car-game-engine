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
    private Environment environment;
/*
    <<<<<<< HEAD
    private ApplicationColor color;
=======
*/
    private List<Car> policeList = new ArrayList<Car>();
    private List<Vector2> policePositions = new ArrayList<Vector2>();

    public GameApplication (Environment environment, List<Vector2> policePositions) {
        this.environment = environment;
        this.policePositions = policePositions;
    }
//>>>>>>> develop

    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
        appEnv.getGraphics().setCamera(new GameCamera());
        car = new Player(appEnv);
        /*
<<<<<<< HEAD
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
=======
        */
        createPolice(policePositions); //Can't currently be done in constructor as player needs appenv and police needs player
    }

    private void createPolice(List<Vector2> vectors) {
        for (Vector2 vector: vectors) {
            Car police = new Police(car);
            police.setPosition(vector);
            policeList.add(police);
        }
    }
    public void update(float delta) {
        car.move(delta);
        for (Car police: policeList) {
            police.move(delta);
        }

        if (entityCollides(car, environment)) {
            car.revert();
        }
    }
    public void render() {
        for (GraphicalTriangle triangle : environment.getGraphicalTriangles()) {
            appEnv.getGraphics().renderTriangle(triangle);
        }

        for (Car police: policeList) {
            appEnv.getGraphics().renderImage(police);
        }
        appEnv.getGraphics().renderImage(car);
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
