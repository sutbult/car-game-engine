package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.app.ApplicationCamera;
import se.chalmers.tda367_4.game.entities.power_ups.*;
import se.chalmers.tda367_4.geometry.color.ApplicationColor;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationKey;
import se.chalmers.tda367_4.game.entities.Car;
import se.chalmers.tda367_4.game.entities.Player;
import se.chalmers.tda367_4.geometry.triangle.GraphicalTriangle;
import se.chalmers.tda367_4.scenes.Scene;
import se.chalmers.tda367_4.geometry.vector.Vector2;

import se.chalmers.tda367_4.game.entities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static se.chalmers.tda367_4.game.entities.Utilities.entityCollides;

public class GameScene implements Scene {
    private ApplicationEnvironment appEnv;
    private Car car;
    private Environment environment;
    private Score score;
    private HudCamera hudCamera;
    private GameCamera gameCamera;

    private List<Vector2> policePositions = new ArrayList<Vector2>();
    private boolean changeScene = false;
    private boolean pauseScene = false;
    private Scene endScene;

    private PowerUpContainer powerUpContainer;
    private PoliceContainer policeContainer;

    private Multiplier playerSpeed = new Multiplier(6);

    private float[] worldBorders;

    private static final float POLICE_SPAWN_TIME = 7;
    private float policeSpawnTime = POLICE_SPAWN_TIME;

    public GameScene(Environment environment, List<Vector2> policePositions, float[] worldBorders) {
        this.environment = environment;
        this.policePositions = policePositions;

        this.powerUpContainer = new PowerUpContainer(
                environment,
                worldBorders[0],
                worldBorders[1],
                worldBorders[2],
                worldBorders[3]
        );

        this.worldBorders = worldBorders;

        hudCamera = new HudCamera();
        gameCamera = new GameCamera();
        score = new Score(0, 1);
    }

    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
        score = new Score(0, 1);
        appEnv.getGraphics().setCamera(new GameCamera());
        car = new Player(appEnv, playerSpeed);
        appEnv.getGraphics().setBackgroundColor(new ApplicationColor(64, 192, 0));

        this.policeContainer = new PoliceContainer(
                environment,
                worldBorders[0],
                worldBorders[1],
                worldBorders[2],
                worldBorders[3],
                gameCamera,
                car);
        createPolice(policePositions);
    }

    private void createPolice(List<Vector2> vectors) {
        for (Vector2 vector: vectors) {
            policeContainer.createPolice(vector);
        }
    }

    public void update(float delta) {
        if (appEnv.getInput().isKeyPressed(ApplicationKey.ESC)) {
            pauseScene = !pauseScene;
        }

        if(pauseScene) return;

        changeScene = false;
        car.move(delta);
        score.update(delta * 2);

        for (Car police : policeContainer.getPolice()) {
            police.move(delta);
        }

        if (entityCollides(car, environment)) {
            car.revert();

        }
        for (Car police : policeContainer.getPolice()) {
            if (entityCollides(car, police)) {
                try {
                    score.saveScore();
                    //setReplacementScene(new MenuScene(score));
                    changeScene = true;
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (entityCollides(police, environment)) {
                police.revert();
            }
        }


        policeSpawnTime -= delta;
        if (policeSpawnTime < 0) {
            policeContainer.createPolice();
            policeSpawnTime = POLICE_SPAWN_TIME;
        }

        handlePowerups();
    }

    private void handlePowerups() {

        if (Math.random() < 0.005) {
            powerUpContainer.createPowerUp();
        }


        Iterator<PowerUp> iterator = powerUpContainer.getPowerUps().iterator();
        while (iterator.hasNext()) {
            PowerUp powerUp = iterator.next();
            if (entityCollides(powerUp, car)) {
                if (powerUp instanceof PlayerSpeedBoost) {
                    playerSpeed.setMultiplier(powerUp.getMultiplier(), powerUp.getDuration());
                } else if (powerUp instanceof ScoreMultiplier) {
                    score.setMultiplier(powerUp.getMultiplier(), powerUp.getDuration());
                } else if (powerUp instanceof ScoreBoost) {
                    score.addScore(powerUp.getMultiplier());
                }
                iterator.remove();
            }
        }
    }

    public void render(){
        appEnv.getGraphics().setCamera(gameCamera);

        for (GraphicalTriangle triangle : environment.getGraphicalTriangles()) {
            appEnv.getGraphics().renderTriangle(triangle);
        }

        for (Car police: policeContainer.getPolice()) {
            appEnv.getGraphics().renderImage(police);
        }
        appEnv.getGraphics().renderImage(car);


        for (PowerUp powerUp: powerUpContainer.getPowerUps()) {
            appEnv.getGraphics().renderImage(powerUp);
        }
        appEnv.getGraphics().setCamera(hudCamera);

        appEnv.getGraphics().renderText(new GameText("Score: " + Math.round(score.getScore()), "Sans_Serif",
                new Vector2(-4.9f,-4.5f),
                        0.8f,
                        false,
                        new ApplicationColor(0,0,0)));

        if(pauseScene){
            appEnv.getGraphics().renderText(new GameText(
                    "PAUSE",
                    "Sans-serif",
                    new Vector2(0,1.8f),
                    2,
                    false,
                    new ApplicationColor(250, 0, 0)
            ));
        }
    }

    public Scene newScene() {
        return changeScene ? endScene : null;
    }

    public void setReplacementScene(Scene newScene){
        this.endScene = newScene;
    }

    public float getScore() {
        return score.getScore();
    }

    private class GameCamera implements ApplicationCamera {

        public Vector2 getPosition() {
            // What is supposed to be returned when the environment
            // has been added:
            return car.getPosition();
            //return new Vector2(0, 0);
        }

        public float getHeight() {
            return 13;
        }
    }

    private class HudCamera implements ApplicationCamera{

        public Vector2 getPosition(){
            return new Vector2(0, 0);
        }

        public float getHeight(){
            return 10;
        }
    }
}

