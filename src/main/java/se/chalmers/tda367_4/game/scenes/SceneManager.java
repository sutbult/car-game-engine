package se.chalmers.tda367_4.game.scenes;

import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.game.GameApplication;
import se.chalmers.tda367_4.game.MenuApplication;

/**
 * Created by Marcus on 2016-04-15.
 */
public class SceneManager implements SceneI {

    private SceneI menu;
    private SceneI game;
    private SceneI scene;
    private ApplicationEnvironment myAppEnv;

    public SceneManager(){
        game = new GameApplication();
        menu = new MenuApplication();
        scene = game;
    }

    public SceneI getScene(){
        return scene;
    }

    public void changeScene(SceneI newScene) throws IllegalArgumentException{
        if(newScene.getClass().equals(menu)){
            scene = menu;
        }
        else if(newScene.getClass().equals(game)){
            scene = game;
        }
        else throw new IllegalArgumentException("You can only change between game and menu state");
    }

    public void init(ApplicationEnvironment appEnv) {
        myAppEnv = appEnv;
        scene.init(myAppEnv);
    }

    public void update(float delta) {
        scene.update(delta);
    }

    public void render() {
        scene.render();
    }
}
