package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.app.ApplicationColor;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.app.ApplicationImage;
import se.chalmers.tda367_4.geometry.Vector2;
import se.chalmers.tda367_4.scenes.Scene;

public class MenuApplication implements Scene {

    private ApplicationEnvironment appEnv;
    private GameText playText = new GameText("Play", "Sans-Serif", new Vector2(0, 0), 1.2f, false,
            new ApplicationColor(0,0,0));
    private GameText quitText = new GameText("Quit", "Sans-Serif", new Vector2(0, -1.2f), 1, false,
            new ApplicationColor(0,0,0));


    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
    }

    public void update(float delta){

    }

    public void render() {
        appEnv.getGraphics().renderText(playText);
        appEnv.getGraphics().renderText(quitText);
    }

    public Scene newScene() {
        return null;
    }
}
