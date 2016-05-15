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

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameApplication implements Scene {
    private ApplicationEnvironment appEnv;
    private Car car;
    private Car police;
    private Environment environment;
    private File file = new File("res/highscores.txt");
    private GameHud score = new GameHud("Score: ", "Sans_Serif", new Vector2(0, 9.2f), 0.8f, false);

    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
        appEnv.getGraphics().setCamera(new GameCamera());
        car = new Player(appEnv);
        police = new Police(appEnv, car);

        GraphicalTriangle triangle = new GraphicalTriangleImpl(new Vector2(4, 4), new Vector2(4, 2), new Vector2(0, 4),
                0.1f, 0.3f, 0.1f);
        GraphicalTriangle triangle2 = new GraphicalTriangleImpl(new Vector2(4, 4), new Vector2(2, 2), new Vector2(2, 4),
                0.5f, 0.0f, 0.9f);
        GraphicalTriangle triangle3 = new GraphicalTriangleImpl(new Vector2(-3, -3), new Vector2(-1, -2), new Vector2(0, 0),
                0.5f, 0.0f, 0.9f);
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
        appEnv.getGraphics().renderText(new GameText("Example", "Serif", new Vector2(1, 1), 1, false));
        appEnv.getGraphics().renderHud(new GameHud("GTFA", "Sans_Serif", new Vector2(0, 0), 1, false), false);
        appEnv.getGraphics().renderHud(score, true);

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
    }}

