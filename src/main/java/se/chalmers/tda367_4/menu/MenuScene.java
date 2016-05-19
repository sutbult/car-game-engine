package se.chalmers.tda367_4.menu;

import se.chalmers.tda367_4.app.ApplicationCamera;
import se.chalmers.tda367_4.game.GameScene;
import se.chalmers.tda367_4.game.GameText;
import se.chalmers.tda367_4.game.WorldLoader;
import se.chalmers.tda367_4.geometry.ApplicationColor;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationKey;
import se.chalmers.tda367_4.geometry.Vector2;
import se.chalmers.tda367_4.scenes.Scene;

public class MenuScene implements Scene {

    private ApplicationEnvironment appEnv;
    private GameText playText = new GameText("Play (1)", "Sans-Serif", new Vector2(0, 0), 0.9f, false,
            new ApplicationColor(0,0,0));
    private GameText quitText = new GameText("Quit (2)", "Sans-Serif", new Vector2(0, -1.2f), 0.9f, false,
            new ApplicationColor(0,0,0));
    private boolean changeScene = false;
    private MenuCamera menuCamera;

    public MenuScene(){
        menuCamera = new MenuCamera();
    }


    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
    }

    public void update(float delta){
    }

    public void render() {
        appEnv.getGraphics().setCamera(menuCamera);
        appEnv.getGraphics().renderText(playText);
        appEnv.getGraphics().renderText(quitText);
    }

    public Scene newScene() {
        if(changeScene){
            WorldLoader worldLoader = new WorldLoader();
            GameScene endScene = worldLoader.createWorld("world1");
            return endScene;
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
