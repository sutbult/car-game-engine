package se.chalmers.tda367_4.game;

public class Score extends Multiplier {
    private float score;

    public Score(float score, float multiplier){
        super(multiplier);
        this.score = score;
    }

    public float getScore(){
        return score;
    }

    public void addScore(float score){
        this.score += score;
    }

    public void update(float delta){
        addScore(delta * getMultiplier());
    }
}
