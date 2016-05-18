package se.chalmers.tda367_4.game.entities.PowerUps;

import se.chalmers.tda367_4.geometry.Vector2;

public class ScoreMultiplier extends PowerUp {
    private float modifier;
    private int duration;

    public ScoreMultiplier(Vector2 position, float modifier, int duration) {
        super(position, "unicorn.png");
        this.modifier = modifier;
        this.duration = duration;
    }

    @Override
    public float getMultiplier() {
        return modifier;
    }

    @Override
    public int getDuration() {
        return duration;
    }
}
