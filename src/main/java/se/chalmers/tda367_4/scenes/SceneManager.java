package se.chalmers.tda367_4.scenes;

import se.chalmers.tda367_4.app.Application;
import se.chalmers.tda367_4.app.ApplicationEnvironment;

public class SceneManager implements Application {
    private Scene scene;
    private ApplicationEnvironment myAppEnv;

    public SceneManager(Scene startScene) {
        scene = startScene;
    }
    public Scene getScene() {
        return scene;
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
        Scene newScene = scene.newScene();
        if(newScene != null) {
            scene = newScene;
            scene.init(myAppEnv);
        }
    }
}
