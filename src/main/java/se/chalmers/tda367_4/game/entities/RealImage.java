package se.chalmers.tda367_4.game.entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RealImage{

    private String fileName;
    public RealImage(String src){
        fileName = src;
    }
    public Image display(){
        String path = "res/" + fileName;
        File file = new File(path);
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
