package se.chalmers.tda367_4.game.entities;

import java.awt.*;

public class ProxyImage{
    private RealImage image;
    private String fileName;

    public ProxyImage(String src){
        fileName = src;
        image = new RealImage(fileName);
    }
    public Image display(){
        return image.display();
    }
}
