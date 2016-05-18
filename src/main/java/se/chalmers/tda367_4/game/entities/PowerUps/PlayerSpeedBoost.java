package se.chalmers.tda367_4.game.entities.PowerUps;

import se.chalmers.tda367_4.geometry.Vector2;

public class PlayerSpeedBoost extends PowerUp {
    private float modifier;
    private int duration;

    public PlayerSpeedBoost(Vector2 position, float modifier, int duration) {
        super(position, "quake-stomp.png");
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
