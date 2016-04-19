package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.app.Application;
import se.chalmers.tda367_4.app.ApplicationCamera;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.game.entities.Car;
import se.chalmers.tda367_4.game.entities.Player;
import se.chalmers.tda367_4.game.entities.Police;
import se.chalmers.tda367_4.geometry.Vector2;
import se.chalmers.tda367_4.swingapp.SwingApplication;

public class GameApplication implements Application {
    public static void main(String[] args) {
        new SwingApplication(new GameApplication());
    }
    private ApplicationEnvironment appEnv;
    private Car car;
    protected Police police;
    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
        appEnv.getGraphics().setCamera(new GameCamera());
        car = new Player(appEnv);
        police = new Police(appEnv, car);
    }
    public void update(float delta) {
        car.move(delta);
        police.move(delta);
    }

    public void render() {
        appEnv.getGraphics().renderImage(car);
    }
    private class GameCamera implements ApplicationCamera {
        public Vector2 getPosition() {
            // What is supposed to be returned when the environment
            // has been added:
            //return car.getPosition();
            return new Vector2(0, 0);
        }
        public float getHeight() {
            return 10;
        }
    }
}
