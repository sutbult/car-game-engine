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

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameApplication implements Scene {
    private ApplicationEnvironment appEnv;
    private Car car;
    private Environment environment;
    private Score score;
    private GameCamera hudCamera;

    private File file = new File("res/highscores.txt");
    private List<Car> policeList = new ArrayList<Car>();
    private List<Vector2> policePositions = new ArrayList<Vector2>();

    public GameApplication (Environment environment, List<Vector2> policePositions) {
        this.environment = environment;
        this.policePositions = policePositions;
    }

    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
        appEnv.getGraphics().setCamera(new GameCamera());
        car = new Player(appEnv);
        score = new Score(0, 1);
        hudCamera = new GameCamera();
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
        score.update(delta);

        for (Car police: policeList) {
            police.move(delta);
        }

        if (entityCollides(car, environment)) {
            car.revert();
        }

        for (Car police: policeList) {
            if (entityCollides(car, police)) {
                appEnv.stop();
            }
            if (entityCollides(police, environment)) {
                police.revert();
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
        appEnv.getGraphics().renderText(new GameText("Example", "Serif", new Vector2(1, 1), 1, false, new ApplicationColor(0,0,0)));
        appEnv.getGraphics().renderText(new GameText("Score: " + Math.round(score.getScore()), "Sans_Serif",
                new Vector2(hudCamera.getPosition().getX() - 4.8f,
                        hudCamera.getPosition().getY() - 4.5f),
                        0.8f,
                        false,
                        new ApplicationColor(0,0,0)));
    }

    /*
    public void printScoreToFile(String score) {
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter out;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println(score);
            out.close();

        }catch (IOException e) {
            e.printStackTrace();
        }


        Why does this segment say 'out' has not been initialized?
        finally{
            try{
                if(out != null){
                    out.close();
                }System.out.println("PrintWriter closed");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }*/

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

