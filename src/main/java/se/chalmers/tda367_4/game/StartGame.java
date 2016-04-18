package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.scenes.SceneManager;
import se.chalmers.tda367_4.swingapp.SwingApplication;

/**
 * Created by Marcus on 2016-04-15.
 */
public class StartGame {

    public static void main(String[] args){
        SceneManager scene = new SceneManager(new GameApplication());
        new SwingApplication(scene);
    }
}
