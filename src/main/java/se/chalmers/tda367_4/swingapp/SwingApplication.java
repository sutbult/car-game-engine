package se.chalmers.tda367_4.swingapp;

import se.chalmers.tda367_4.app.*;
import se.chalmers.tda367_4.geometry.GraphicalTriangle;
import se.chalmers.tda367_4.geometry.Triangle;
import se.chalmers.tda367_4.geometry.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

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
            return vector;
        }
        public void renderImage(ApplicationSprite sprite) {
            ApplicationImage image = sprite.getImage();
            if(image instanceof SwingImage) {
                Vector2 position = project(sprite.getPosition());
                Vector2 bounds = projectScale(sprite.getBounds());
                float rotation = sprite.getRotation();

                position = new Vector2(
                        position.getX(),
                        displayHeight - position.getY()
                );

                SwingImage swingImage = (SwingImage)image;
                g.setTransform(new AffineTransform());
                g.translate(
                        position.getX(),
                        position.getY()
                );
                g.rotate(-rotation);
                g.drawImage(swingImage.getRawImage(),
                        (int)-bounds.getX()/2,
                        (int)-bounds.getY()/2,
                        (int) bounds.getX(),
                        (int) bounds.getY(),
                        null
                );
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
                Vector2 corner = project(corners[i]);
                xPoints[i] = (int)corner.getX();
                yPoints[i] = (int)corner.getY();
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
