package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.app.ApplicationCamera;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.game.entities.Car;
import se.chalmers.tda367_4.game.entities.Player;
import se.chalmers.tda367_4.game.entities.Police;
import se.chalmers.tda367_4.scenes.Scene;
import se.chalmers.tda367_4.geometry.Vector2;

import se.chalmers.tda367_4.game.entities.*;
import se.chalmers.tda367_4.geometry.*;

import java.util.List;

public class GameApplication implements Scene {
    private ApplicationEnvironment appEnv;
    private Car car;
    private Police police;
    private Environment environment;
    private List<Car> policeList;

    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
        appEnv.getGraphics().setCamera(new GameCamera());
        car = new Player(appEnv);
        police = new Police(appEnv, car);

        JSONhandler handler = new JSONhandler();

        List<GraphicalTriangle> list = handler.getSolidTriangles();
        List<GraphicalTriangle> list2 = handler.getSolidTriangles();

        createPolice(handler.getPolice());

        environment = new Environment(list, list2);
    }

    private void createPolice(List<Vector2> vectors) {
        for (Vector2 vector: vectors) {
            Car police = new Police(appEnv);
            police.setPosition(vector);
            policeList.add(police))
        }
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
        appEnv.getGraphics().renderText(new GameText("Example", "Serif", new Vector2(1, 1), 1, false));
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
