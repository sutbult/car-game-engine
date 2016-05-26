package se.chalmers.tda367_4.game.entities;

import java.util.Timer;
import java.util.TimerTask;

public class Multiplier {

    private float currentMultiplier;
    private final float standardMultiplier;
    private Timer timer = new Timer();

    public Multiplier(float modifier) {
        this.currentMultiplier = modifier;
        this.standardMultiplier = modifier;
    }

    public synchronized float getMultiplier() {
        return currentMultiplier;
    }

    public float getStandardMultiplier() {
        return standardMultiplier;
    }

    public synchronized void setMultiplier(final float modifier, int duration) {
        final Multiplier self = this;
        currentMultiplier += modifier;
        timer.schedule(new TimerTask() {
           @Override
           public void run() {
               synchronized (self) {
                   currentMultiplier -= modifier;
               }
           }
                       },
                duration * 1000
        );
    }

    public void resetMultiplier() {
        currentMultiplier = standardMultiplier;
    }

}
