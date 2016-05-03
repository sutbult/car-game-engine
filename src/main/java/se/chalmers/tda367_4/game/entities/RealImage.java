package se.chalmers.tda367_4.game.entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class RealImage{

    private String fileName;
    private HashMap <String, File> map;
    public RealImage(String src){
        fileName = src;
        map = new HashMap<String, File>();

        File yellow = new File("res/car_yellow.png");
        File red = new File("res/car_red.png");
        File black = new File("res/car_black.png");
        File blue = new File("res/car_3_blue.png");

        map.put("yellow", yellow);
        map.put("red", red);
        map.put("black", black);
        map.put("blue", blue);
    }
    public Image display(){

        if(map.containsKey(fileName)){
            File tmp = map.get(fileName);
            BufferedImage image;
            try {
                image = ImageIO.read(tmp);
                return image;
            }catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }else return null;
        /*
        String path = "res/" + fileName;
        File file = new File(path);
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }*/

    }
}
