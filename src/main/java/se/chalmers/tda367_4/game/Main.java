package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.scenes.SceneManager;
import se.chalmers.tda367_4.swingapp.SwingApplication;

public class Main {

    public static void main(String[] args){
        SceneManager scene = new SceneManager(new GameApplication("world1.json"));
        new SwingApplication(scene);
    }
}
