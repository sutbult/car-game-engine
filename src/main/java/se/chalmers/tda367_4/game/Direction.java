package se.chalmers.tda367_4.game;

public enum Direction {
    FORWARD(0, 1),
    FORWARD_LEFT(-1, 1),
    FORWARD_RIGHT(1, 1),

    BACKWARD(0, -1),
    BACK_LEFT(-1, -1),
    BACK_RIGHT(1, -1),

    STOPPED(0, 0),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private int x;
    private int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public static Direction toDirection(boolean forward, boolean backward, boolean left, boolean right) {

        int tempX = 0;
        int tempY = 0;

        if (forward) tempY += 1;
        if (backward) tempY -= 1;
        if (left) tempX -= 1;
        if (right) tempX += 1;

        return getDirection(tempX, tempY);
    }

    private static Direction getDirection(int x, int y){
        Direction tempDir = null;
        for(Direction dir : Direction.values()){
            if(dir.getX() == x && dir.getY() == y) tempDir = dir;
        }
        return tempDir;
    }
}


