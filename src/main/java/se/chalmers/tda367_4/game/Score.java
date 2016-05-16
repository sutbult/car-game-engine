package se.chalmers.tda367_4.game;

public class Score {
    private float score;
    private float multiplier;

    public Score(float score, float multiplier){
        this.score = score;
        this.multiplier = multiplier;
    }

    public float getScore(){
        return score;
    }

    public float getMultiplier(){
        return multiplier;
    }

    public void addScore(float score){
        this.score += score;
    }

    public void setMultiplier(float multiplier){
        this.multiplier = multiplier;
    }

    public void resetMultiplier(){
        multiplier = 1;
    }

    public void update(float delta){
        addScore(delta * multiplier);
    }
}
