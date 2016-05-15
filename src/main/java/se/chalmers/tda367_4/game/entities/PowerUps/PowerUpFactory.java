package se.chalmers.tda367_4.game.entities.PowerUps;

import se.chalmers.tda367_4.game.entities.Environment;
import se.chalmers.tda367_4.game.entities.SolidEntity;
import se.chalmers.tda367_4.geometry.Vector2;

import java.util.List;
import java.util.Random;

import static se.chalmers.tda367_4.Utilities.entityCollides;

public class PowerUpFactory {

    private Environment environment;
    private float maxLocationValue;

    public PowerUpFactory (Environment environment, float maxLocationValue) {
        this.environment = environment;
        this.maxLocationValue = maxLocationValue;
    }

    public void createPowerUp() {
        Random r = new Random();
        Vector2 position = new Vector2(
                r.nextFloat() * (2 * maxLocationValue) - maxLocationValue,
                r.nextFloat() * (2 * maxLocationValue) - maxLocationValue
        );
        PowerUp powerUp = new PowerUp(position);
        while (entityCollides(powerUp, environment) ||
                powerUpCollides(powerUp, environment.getPowerUps())) {
            position = new Vector2(
                    r.nextFloat() * (2 * maxLocationValue) - maxLocationValue,
                    r.nextFloat() * (2 * maxLocationValue) - maxLocationValue
            );
            powerUp = new PowerUp(position);
        }
        environment.addPowerUp(powerUp);
    }

    private boolean powerUpCollides(SolidEntity first, List<PowerUp> second) {

        for (SolidEntity entity : second) {
            if (entityCollides(first, entity)) {
                return true;
            }
        }
        return false;
    }
}
