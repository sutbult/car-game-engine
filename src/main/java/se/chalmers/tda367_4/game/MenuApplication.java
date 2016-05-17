package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.app.ApplicationColor;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationKey;
import se.chalmers.tda367_4.geometry.Vector2;
import se.chalmers.tda367_4.scenes.Scene;

public class MenuApplication implements Scene {

    private ApplicationEnvironment appEnv;
    private GameText playText = new GameText("Play (Space)", "Sans-Serif", new Vector2(0, 0), 1.2f, false,
            new ApplicationColor(0,0,0));
    private GameText quitText = new GameText("Quit (Escape)", "Sans-Serif", new Vector2(0, -1.2f), 1, false,
            new ApplicationColor(0,0,0));
    private boolean changeScene = false;
    private GameApplication game;

    public MenuApplication(GameApplication game){
        this.game = game;
    }


    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
    }

    public void update(float delta){
        if (appEnv.getInput().isKeyDown(ApplicationKey.SPACE)){
            System.out.println("Change Scene 2");
            changeScene = true;
        }

        if (appEnv.getInput().isKeyDown(ApplicationKey.ESC)){
            System.out.println("Change Scene 2");
            System.exit(0);
        }

    }

    public void render() {
        appEnv.getGraphics().renderText(playText);
        appEnv.getGraphics().renderText(quitText);
    }

    public Scene newScene() {
        if(changeScene){
            System.out.println("Change Scene 2");
            return game;
        }else return null;
    }
}
