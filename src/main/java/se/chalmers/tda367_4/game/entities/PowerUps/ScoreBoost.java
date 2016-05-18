package se.chalmers.tda367_4.game.entities.PowerUps;

import se.chalmers.tda367_4.geometry.Vector2;

public class ScoreBoost extends PowerUp {
    private float boost;

    public ScoreBoost(Vector2 position, float boost) {
        super(position, "minerals.png");
        this.boost = boost;
    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public float getMultiplier() {
        return boost;
    }
}
