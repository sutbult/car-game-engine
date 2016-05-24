package se.chalmers.tda367_4.main;

import se.chalmers.tda367_4.menu.MenuScene;
import se.chalmers.tda367_4.scenes.SceneManager;
import se.chalmers.tda367_4.swingapp.SwingApplication;

public class Main {

    public static void main(String[] args){
        SceneManager scene = new SceneManager(new MenuScene());
        new SwingApplication(scene);
    }
}
