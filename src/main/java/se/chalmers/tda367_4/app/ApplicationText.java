package se.chalmers.tda367_4.app;

import se.chalmers.tda367_4.geometry.Vector2;

public class ApplicationText {

    private String text;
    private String font;
    private Vector2 position;
    private boolean isBold;
    private float height;

    public ApplicationText(String text, String font, Vector2 position, float height, boolean isBold){
        this.text = text;
        this.font = font;
        this.position = position;
        this.height = height;
        this.isBold = isBold;
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

}
