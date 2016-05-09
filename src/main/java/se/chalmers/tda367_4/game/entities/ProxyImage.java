package se.chalmers.tda367_4.game.entities;

import java.awt.*;

public class ProxyImage{
    private RealImage image = new RealImage();
    private String fileName;

    public ProxyImage(String src){
        fileName = src;
        image = image.getInstance();
    }
    public Image display(){
        return image.display(this.fileName);
    }
}
