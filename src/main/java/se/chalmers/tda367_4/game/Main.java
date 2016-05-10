package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.scenes.SceneManager;
import se.chalmers.tda367_4.swingapp.SwingApplication;

public class Main {

    public static void main(String[] args){
        WorldLoader worldLoader = new WorldLoader();
        SceneManager scene = new SceneManager(worldLoader.createWorld("world1"));
        new SwingApplication(scene);
    }
}
