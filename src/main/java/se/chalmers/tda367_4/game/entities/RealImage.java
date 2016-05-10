package se.chalmers.tda367_4.game.entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class RealImage implements Proxy{

    private String fileName;
    private RealImage realImage;
    private HashMap <String, File> map = new HashMap<String, File>();

    private File yellow = new File("res/car_yellow.png");
    private File red = new File("res/car_red.png");
    private File black = new File("res/car_black.png");
    private File blue = new File("res/car_3_blue.png");

    public RealImage(){
        if(map.isEmpty()){
            map.put("yellow", yellow);
            map.put("red", red);
            map.put("black", black);
            map.put("blue", blue);
        }
    }

    public RealImage getInstance(){
        if(realImage == null){
            realImage = new RealImage();
            return realImage;
        }else return realImage;
    }

    public Image display(){
        if(map.containsKey(fileName)){
            File tmp = map.get(fileName);
            return readFile(tmp);
        }else System.out.println("Image not found"); return null;
    }

    public Image display(String fileName){
        this.fileName = fileName;
        return display();

    }

    private Image readFile(File file){
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            return image;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
