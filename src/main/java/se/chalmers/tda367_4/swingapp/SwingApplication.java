package se.chalmers.tda367_4.swingapp;

import se.chalmers.tda367_4.app.*;

import se.chalmers.tda367_4.geometry.GraphicalTriangle;
import se.chalmers.tda367_4.geometry.Vector2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

public class SwingApplication extends JPanel implements Runnable {
    private int displayWidth;
    private int displayHeight;
    private Image displayImage;
    private SwingGraphics swingGraphics;
    private SwingInput swingInput;
    private Application application;

    public SwingApplication(Application application) {
        displayWidth = 800;
        displayHeight = 600;
        displayImage = null;

        this.application = application;
        swingGraphics = new SwingGraphics();
        swingInput = new SwingInput();
        launch();
    }
    private void launch() {
        JFrame frame = new JFrame();
        frame.setContentPane(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(swingInput);
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
            return swingInput;
        }
    }

    private class SwingGraphics implements ApplicationGraphics {
        public Graphics2D g;
        private ApplicationCamera camera;
        private HashMap<String, Image> map = new HashMap<String, Image>();

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
        public void setCamera(ApplicationCamera camera) {
            this.camera = camera;
        }
        private float projectScalar() {
            if(camera == null) {
                return 1;
            }
            else {
                return displayHeight / camera.getHeight();
            }
        }
        private Vector2 projectScale(Vector2 vector) {
            return vector.multiply(projectScalar());
        }
        private Vector2 project(Vector2 vector) {
            if(camera == null) {
                return vector;
            }
            vector = vector.subtract(camera.getPosition());
            vector = projectScale(vector);
            vector = vector.add(new Vector2(
                    displayWidth / 2,
                    displayHeight / 2
            ));
            vector = new Vector2(
                    vector.getX(),
                    displayHeight - vector.getY()
            );
            return vector;
        }



        public void renderImage(ApplicationSprite sprite) {
            ApplicationImage image = sprite.getImage();

                Vector2 position = project(sprite.getPosition());
                Vector2 bounds = projectScale(sprite.getBounds());
                float rotation = sprite.getRotation();

                g.setTransform(new AffineTransform());
                g.translate(
                        position.getX(),
                        position.getY()
                );
                g.rotate(-rotation);

                Image swingImage = map.get(image.getPath());
                if(swingImage == null){
                    ApplicationImage path = loadImage(image.getPath());
                    swingImage = map.get(path.getPath());
                }

                g.drawImage(swingImage,
                        (int)-bounds.getX()/2,
                        (int)-bounds.getY()/2,
                        (int) bounds.getX(),
                        (int) bounds.getY(),
                        null
                );
        }

        private ApplicationImage loadImage(String src) {
                File file = new File(src);
                BufferedImage image;
                try {
                    image = ImageIO.read(file);
                    map.put(src, image);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
                return new ApplicationImage(src);

        }

        public void renderTriangle(GraphicalTriangle triangle) {
            Vector2[] corners = triangle.getCorners();
            int[] xPoints = new int[3];
            int[] yPoints = new int[3];
            for(int i = 0; i < 3; i++) {
                Vector2 corner = project(corners[i]);
                xPoints[i] = (int)corner.getX();
                yPoints[i] = (int)corner.getY();
            }
            g.setTransform(new AffineTransform());
            g.setColor(new Color(
                    (int)(triangle.getR() * 255),
                    (int)(triangle.getG() * 255),
                    (int)(triangle.getB() * 255)
            ));
            g.fillPolygon(xPoints, yPoints, 3);
        }

        public void renderText(ApplicationText text){
            Font font = new Font(text.getFont(), Font.PLAIN, (int)(text.getHeight() * projectScalar()));
            Vector2 position;

            position = text.getPosition();
            position = project(position);
            FontMetrics metrics = g.getFontMetrics(font);

            position = position.add(new Vector2(
                    -metrics.stringWidth(text.getText())/2,
                    metrics.getHeight() / 4));

            updateText(text, position, font);
        }

        private void updateText(ApplicationText text, Vector2 position, Font font){
            g.setTransform(new AffineTransform());
            g.setFont(font);
            g.setColor(new Color(0,0,0));
            g.drawString(text.getText(), position.getX(), position.getY());
        }
    }

    private class SwingInput implements ApplicationInput, KeyListener {
        private boolean[] states;

        public SwingInput() {
            states = new boolean[ApplicationKey.values().length];
        }
        public boolean isKeyDown(ApplicationKey key) {
            return states[key.getId()];
        }
        public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {
            onEvent(e, true);
        }
        public void keyReleased(KeyEvent e) {
            onEvent(e, false);
        }
        private void onEvent(KeyEvent e, boolean down) {
            ApplicationKey key;
            switch(e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    key = ApplicationKey.UP;
                    break;
                case KeyEvent.VK_DOWN:
                    key = ApplicationKey.DOWN;
                    break;
                case KeyEvent.VK_LEFT:
                    key = ApplicationKey.LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                    key = ApplicationKey.RIGHT;
                    break;
                default:
                    key = null;
            }
            if(key != null) {
                states[key.getId()] = down;
            }
        }
    }
}
