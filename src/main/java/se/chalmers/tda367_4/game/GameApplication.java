package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.app.ApplicationCamera;
import se.chalmers.tda367_4.app.ApplicationColor;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.game.entities.Car;
import se.chalmers.tda367_4.game.entities.Player;
import se.chalmers.tda367_4.game.entities.Police;
import se.chalmers.tda367_4.game.entities.PowerUps.PowerUp;
import se.chalmers.tda367_4.game.entities.PowerUps.PowerUpFactory;
import se.chalmers.tda367_4.scenes.Scene;
import se.chalmers.tda367_4.geometry.Vector2;

import se.chalmers.tda367_4.game.entities.*;
import se.chalmers.tda367_4.geometry.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static se.chalmers.tda367_4.Utilities.entityCollides;

public class GameApplication implements Scene {
    private ApplicationEnvironment appEnv;
    private Car car;
    private Environment environment;
    private List<Car> policeList = new ArrayList<Car>();
    private List<Vector2> policePositions = new ArrayList<Vector2>();

    private PowerUpFactory powerUpFactory;

    public GameApplication (Environment environment, List<Vector2> policePositions) {
        this.environment = environment;
        this.policePositions = policePositions;


        powerUpFactory = new PowerUpFactory(environment, 10);

    }

    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
        appEnv.getGraphics().setCamera(new GameCamera());
        car = new Player(appEnv);
        createPolice(policePositions);
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

        for (Car police: policeList) {
            if (entityCollides(police, environment)) {
                police.revert();
            }
        }


        handlePowerups();
    }

    private void handlePowerups() {

        if (Math.random() < 0.01) {
            powerUpFactory.createPowerUp();
        }

        Iterator<PowerUp> iterator = environment.getPowerUps().iterator();
        while (iterator.hasNext()) {
            PowerUp powerUp = iterator.next();
            if (entityCollides(powerUp, car)) {
                iterator.remove();
            }
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


        for (PowerUp powerUp: environment.getPowerUps()) {
            appEnv.getGraphics().renderImage(powerUp);
        }
    }
    public Scene newScene() {
        return null;
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
