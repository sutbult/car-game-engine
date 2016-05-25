package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.app.ApplicationCamera;
import se.chalmers.tda367_4.game.entities.Car;
import se.chalmers.tda367_4.game.entities.Environment;
import se.chalmers.tda367_4.game.entities.Multiplier;
import se.chalmers.tda367_4.game.entities.Police;
import se.chalmers.tda367_4.geometry.vector.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static se.chalmers.tda367_4.game.entities.Utilities.entityCollides;

public class PoliceContainer {

    private Environment environment;
    private float maxXvalue;
    private float maxYvalue;
    private float minXvalue;
    private float minYvalue;
    private ApplicationCamera camera;
    private Car player;
    private List<Police> policeList = new ArrayList<Police>();

    private static final float MAX_SPEED_DIFF = 1.75f;
    private static final float MIN_SPEED_DIFF = 0.25f;

    public PoliceContainer(Environment environment, float maxXvalue, float maxYvalue,
                           float minXvalue, float minYvalue,
                           ApplicationCamera camera, Car player) {
        this.environment = environment;
        this.maxXvalue = maxXvalue;
        this.maxYvalue = maxYvalue;
        this.minXvalue = minXvalue;
        this.minYvalue = minYvalue;
        this.camera = camera;
        this.player = player;
    }

    public List<Police> getPolice() {
        return policeList;
    }

    public void createPolice() {
        Random r = new Random();
        Police police;
        Vector2 position;
        do {

            do {
                position = new Vector2(
                        r.nextFloat() * (maxXvalue - minXvalue) + minXvalue,
                        r.nextFloat() * (maxYvalue - minYvalue) + minXvalue
                );
            } while (!outOfCamera(position));

            police = new Police(player, new Multiplier(generateSpeed()));
            System.out.println(police.getStandardSpeed());

        } while (entityCollides(police, environment));
        policeList.add(police);
    }

    public void createPolice(Vector2 position) {
        Police police = new Police(player, new Multiplier(generateSpeed()));
        police.setPosition(position);
        policeList.add(police);
    }

    private float generateSpeed() {
        return (float) Math.random() * MAX_SPEED_DIFF + (player.getStandardSpeed() - (MAX_SPEED_DIFF + MIN_SPEED_DIFF));
    }

    private boolean outOfCamera(Vector2 position) {
        if (position.getY() > camera.getPosition().getY() + camera.getHeight() + player.getBounds().getY()) {
            return true;
        } else if (position.getY() < camera.getPosition().getY() - camera.getHeight() - player.getBounds().getY()) {
            return true;
        } else if (position.getX() > camera.getPosition().getX() + camera.getHeight() + player.getBounds().getX()) {
            return true;
        } else if (position.getX() < camera.getPosition().getX() - camera.getHeight() - player.getBounds().getX()) {
            return true;
        }
        return false;
    }
}
