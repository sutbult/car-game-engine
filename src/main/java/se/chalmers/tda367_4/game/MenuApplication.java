package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.app.ApplicationCamera;
import se.chalmers.tda367_4.geometry.ApplicationColor;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationKey;
import se.chalmers.tda367_4.geometry.Vector2;
import se.chalmers.tda367_4.scenes.Scene;

public class MenuApplication implements Scene {

    private ApplicationEnvironment appEnv;
    private GameText playText = new GameText("Play (1)", "Sans-Serif", new Vector2(0, 0), 0.9f, false,
            new ApplicationColor(0,0,0));
    private GameText quitText = new GameText("Quit (2)", "Sans-Serif", new Vector2(0, -1.2f), 0.9f, false,
            new ApplicationColor(0,0,0));
    private boolean changeScene = false;
    private MenuCamera menuCamera;

    public MenuApplication(){
        menuCamera = new MenuCamera();
    }


    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
    }

    public void update(float delta){
        if (appEnv.getInput().isKeyDown(ApplicationKey.ONE)){
            System.out.println("Pressed play");
            changeScene = true;
        }

        if (appEnv.getInput().isKeyDown(ApplicationKey.TWO)){
            System.out.println("Pressed quit");
            System.exit(0);
        }

    }

    public void render() {
        appEnv.getGraphics().setCamera(menuCamera);
        appEnv.getGraphics().renderText(playText);
        appEnv.getGraphics().renderText(quitText);
    }

    public Scene newScene() {
        if(changeScene){
            System.out.println("Change Scene");
            WorldLoader worldLoader = new WorldLoader();
            return worldLoader.createWorld("world1");
        }else return null;
    }

    private class MenuCamera implements ApplicationCamera{

        public Vector2 getPosition() {
            return new Vector2(0, 0);
        }

        public float getHeight() {
            return 10;
        }
    }


}
