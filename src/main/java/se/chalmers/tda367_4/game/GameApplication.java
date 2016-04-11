package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.app.Application;
import se.chalmers.tda367_4.app.ApplicationEnvironment;
import se.chalmers.tda367_4.game.entities.Car;
import se.chalmers.tda367_4.swingapp.SwingApplication;

public class GameApplication implements Application {
    public static void main(String[] args) {
        new SwingApplication(new GameApplication());
    }
    private ApplicationEnvironment appEnv;
    private Car car;
    public void init(ApplicationEnvironment appEnv) {
        this.appEnv = appEnv;
        car = new Car(appEnv);
    }
    public void update(float delta) {
        car.move(delta);
    }

    public void render() {
        appEnv.getGraphics().renderImage(
                car.getImage(),
                (int)car.getPosition().getX(),
                (int)car.getPosition().getY(),
                (int)car.getBounds().getX(),
                (int)car.getBounds().getY(),
                car.getRotation()
        );
    }
}
