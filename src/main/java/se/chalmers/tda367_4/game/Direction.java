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
    public static Direction toDirection(boolean forward, boolean backward, boolean left, boolean right){

        if(forward && !backward && !left && !right){
            return FORWARD;
        }

        if(!forward && backward && !left && !right){
            return BACKWARD;
        }

        if(forward && !backward && left && !right){
            return FORWARD_LEFT;
        }

        if(forward && !backward && !left && right){
            return FORWARD_RIGHT;
        }

        if(!forward && backward && left && !right){
            return BACK_LEFT;
        }

        if(!forward && backward && !left && right){
            return BACK_RIGHT;
        }

        if(forward && !backward && left && right){
            return FORWARD;
        }

        if(!forward && backward && left && right){
            return BACKWARD;
        }

        else return STOPPED;
    }

}
