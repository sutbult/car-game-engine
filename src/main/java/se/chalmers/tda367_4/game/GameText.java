package se.chalmers.tda367_4.game;

import se.chalmers.tda367_4.geometry.ApplicationColor;
import se.chalmers.tda367_4.app.ApplicationText;
import se.chalmers.tda367_4.geometry.Vector2;

public class GameText implements ApplicationText {
    private String text;
    private String font;
    private Vector2 position;
    private boolean isBold;
    private float height;
    private ApplicationColor color;

    public GameText(String text, String font, Vector2 position, float height, boolean isBold, ApplicationColor color){
        this.text = text;
        this.font = font;
        this.position = position;
        this.height = height;
        this.isBold = isBold;
        this.color = color;
    }

    public String getText(){
        return text;
    }

    public String getFont(){
        return font;
    }

    public Vector2 getPosition(){
        return position;
    }

    public float getHeight(){
        return height;
    }

    public boolean isBold(){
        return isBold;
    }

    public ApplicationColor getColor() {
        return color;
    }
}
