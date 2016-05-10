package se.chalmers.tda367_4.game.entities;

import java.awt.*;

public class ProxyImage implements Proxy{
    private RealImage image = new RealImage();
    private String fileName;

    public ProxyImage(String src){
        fileName = src;
        image = image.getInstance();
    }
    public Image display(){
        image = image.getInstance();
        return image.display(this.fileName);
    }
}
