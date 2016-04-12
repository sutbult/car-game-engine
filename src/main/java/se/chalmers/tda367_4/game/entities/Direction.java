package se.chalmers.tda367_4.game.entities;

/**
 * Created by Marcus on 2016-04-11.
 */
public enum Direction {
    FORWARD,
    BACKWARD,

    FORWARD_LEFT,
    FORWARD_RIGHT,

    BACK_LEFT,
    BACK_RIGHT,

    STOPPED;

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
