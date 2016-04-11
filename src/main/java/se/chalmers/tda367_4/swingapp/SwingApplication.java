package se.chalmers.tda367_4.swingapp;

import se.chalmers.tda367_4.app.*;
import se.chalmers.tda367_4.geometry.GraphicalTriangle;
import se.chalmers.tda367_4.geometry.Triangle;
import se.chalmers.tda367_4.geometry.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class SwingApplication extends JPanel implements Runnable {
    /*public static void main(String[] args) {
        new SwingApplication(null);
    }*/

    private int displayWidth;
    private int displayHeight;
    private Image displayImage;
    private SwingGraphics swingGraphics;
    private Application application;

    public SwingApplication(Application application) {
        displayWidth = 800;
        displayHeight = 600;
        displayImage = null;

        this.application = application;
        swingGraphics = new SwingGraphics();
        launch();
    }
    private void launch() {
        JFrame frame = new JFrame();
        frame.setContentPane(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Thread thread = new Thread(this);
        thread.start();
    }
    public Dimension getPreferredSize() {
        return new Dimension(displayWidth, displayHeight);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(displayImage != null) {
            g.drawImage(displayImage, 0, 0, displayWidth, displayHeight, null);
        }
    }
    public void run() {
        application.init(new SwingEnvironment());

        long lastTime = System.currentTimeMillis();
        while(true) {
            long time = System.currentTimeMillis();
            long delta = time - lastTime;
            lastTime = time;

            swingGraphics.beginRendering();
            application.update(delta / 1000.f);
            application.render();
            repaint();
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private class SwingEnvironment implements ApplicationEnvironment {

        public ApplicationGraphics getGraphics() {
            return swingGraphics;
        }

        public ApplicationInput getInput() {
            return null;
        }
    }
    private class SwingGraphics implements ApplicationGraphics {
        public Graphics2D g;

        public void updateSize() {
            Dimension d = getSize();
            displayWidth = d.width;
            displayHeight = d.height;
        }
        public void beginRendering() {
            updateSize();
            displayImage = createImage(displayWidth, displayHeight);
            g = (Graphics2D)displayImage.getGraphics();
        }

        public void renderImage(ApplicationImage image, int x, int y, int w, int h, float r) {
            if(image instanceof SwingImage) {
                y = displayHeight - y;
                SwingImage swingImage = (SwingImage)image;
                g.setTransform(new AffineTransform());
                g.translate(x, y);
                g.rotate(-r);
                g.drawImage(swingImage.getRawImage(), -w/2, -h/2, w, h, null);
            }
            else {
                throw new RuntimeException("Unsupported type of image");
            }
        }
        public ApplicationImage loadImage(String src) {
            if(src.equals("orange")) {
                Image img = createImage(100, 100);
                Graphics g = img.getGraphics();
                g.setColor(new Color(255, 128, 0));
                g.fillRect(0, 0, 100, 100);
                return new SwingImage(img);
            }
            else {
                return null;
            }
        }
        public void renderTriangle(GraphicalTriangle triangle) {
            Vector2[] corners = triangle.getCorners();
            int[] xPoints = new int[3];
            int[] yPoints = new int[3];
            for(int i = 0; i < 3; i++) {
                xPoints[i] = (int)corners[i].getX();
                yPoints[i] = (int)corners[i].getY();
                yPoints[i] = displayHeight - yPoints[i];
            }
            g.setTransform(new AffineTransform());
            g.setColor(new Color(
                    (int)(triangle.getR() * 255),
                    (int)(triangle.getG() * 255),
                    (int)(triangle.getB() * 255)
            ));
            g.fillPolygon(xPoints, yPoints, 3);
        }
    }
    private class SwingImage implements ApplicationImage {
        private Image rawImage;
        public SwingImage(Image rawImage) {
            this.rawImage = rawImage;
        }
        public Image getRawImage() {
            return rawImage;
        }
    }
    private class SwingInput implements ApplicationInput {

        public boolean isKeyDown(ApplicationKey key) {
            return false;
        }
    }
}
