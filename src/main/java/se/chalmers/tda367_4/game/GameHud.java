package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.app.ApplicationScore;
import se.chalmers.tda367_4.geometry.Vector2;

public class GameHud extends GameText
        implements ApplicationScore{

    private int score = 0;
    private int multiplier = 1;

    public GameHud(String text, String font, Vector2 position, float height, boolean isBold){
        super(text, font, position, height, isBold);
    }

    public int getScore(){
        return score;
    }

    public int getMultiplier(){
        return multiplier;
    }

    public void addScore(){
        score = (score + 1) * multiplier;
    }

    public void addMultiplier(){
        multiplier++;
    }

    public void resetMultiplier(){
        multiplier = 1;
    }
}
