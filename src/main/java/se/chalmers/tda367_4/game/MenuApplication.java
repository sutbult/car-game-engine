package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.game.scenes.SceneI;

public class MenuApplication implements SceneI {

    private ApplicationEnvironment appEnv;


    public void init(ApplicationEnvironment appEnv) {this.appEnv = appEnv;}

    public void update(float delta){}

    public void render() {}

}
