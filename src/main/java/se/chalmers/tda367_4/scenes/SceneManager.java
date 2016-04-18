package se.chalmers.tda367_4.scenes;

import se.chalmers.tda367_4.app.Application;
import se.chalmers.tda367_4.app.ApplicationEnvironment;

/**
 * Created by Marcus on 2016-04-15.
 */
public class SceneManager implements Application {

    private Scene menu;
    private Scene game;
    private Scene scene;
    private ApplicationEnvironment myAppEnv;

    public SceneManager(Scene startScene){
        //game = new GameApplication();
        //menu = new MenuApplication();
        scene = startScene;
    }

    public Scene getScene(){
        return scene;
    }
    /*
    public void changeScene(Scene newScene) throws IllegalArgumentException{
        if(newScene.getClass().equals(menu)){
            scene = menu;
        }
        else if(newScene.getClass().equals(game)){
            scene = game;
        }
        else throw new IllegalArgumentException("You can only change between game and menu state");
    }*/

    public void init(ApplicationEnvironment appEnv) {
        myAppEnv = appEnv;
        scene.init(myAppEnv);
    }

    public void update(float delta) {
        scene.update(delta);
    }

    public void render() {
        scene.render();
        Scene newScene = scene.newScene();
        if(newScene != null) {
            scene = newScene;
            scene.init(myAppEnv);
        }
    }
}
