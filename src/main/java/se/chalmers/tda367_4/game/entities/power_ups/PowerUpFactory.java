package se.chalmers.tda367_4.game.entities.power_ups;

import se.chalmers.tda367_4.game.entities.Environment;
import se.chalmers.tda367_4.game.entities.SolidEntity;
import se.chalmers.tda367_4.geometry.vector.Vector2;

import java.util.List;
import java.util.Random;

import static se.chalmers.tda367_4.game.Utilities.entityCollides;

public class PowerUpFactory {

    private Environment environment;
    private float maxXvalue;
    private float maxYvalue;
    private float minXvalue;
    private float minYvalue;

    public PowerUpFactory (Environment environment, float maxXvalue, float maxYvalue,
                           float minXvalue, float minYvalue) {
        this.environment = environment;
        this.maxXvalue = maxXvalue;
        this.maxYvalue = maxYvalue;
        this.minXvalue = minXvalue;
        this.minYvalue = minYvalue;
    }

    public void createPowerUp() {
        Random r = new Random();
        PowerUp powerUp;
        do {
            Vector2 position = new Vector2(
                    r.nextFloat() * (maxXvalue - minXvalue) + minXvalue,
                    r.nextFloat() * (maxYvalue - minYvalue) + minXvalue
            );
            powerUp = generatePowerUp(position);
        } while (entityCollides(powerUp, environment) ||
                    powerUpCollides(powerUp, environment.getPowerUps()));
        environment.addPowerUp(powerUp);
    }

    private PowerUp generatePowerUp(Vector2 position) {
        double amountOfPowerUps = 3;
        if (Math.random() < 1 / amountOfPowerUps) {
            return new PlayerSpeedBoost(position, 3, 3);
        } else if (Math.random() < 2 / amountOfPowerUps){
            return new ScoreMultiplier(position, 6.5f, 5);
        } else {
            return new ScoreBoost(position, 30);
        }
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
