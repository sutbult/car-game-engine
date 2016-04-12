package se.chalmers.tda367_4.game;

/**
 * Created by Marcus on 2016-04-11.
 */

import org.junit.Test;
import se.chalmers.tda367_4.game.entities.Direction;
import static org.junit.Assert.*;

public class DirectionTest {

    @Test
    public void forward() throws Exception{
        Direction value = Direction.toDirection(true, false, false, false);
        assertEquals(Direction.FORWARD, value);
    }

    @Test
    public void backward() throws Exception{
        Direction value = Direction.toDirection(false, true, false, false);
        assertEquals(Direction.BACKWARD, value);
    }

    @Test
    public void forwardLeft() throws Exception{
        Direction value = Direction.toDirection(true, false, true, false);
        assertEquals(Direction.FORWARD_LEFT, value);
    }

    @Test
    public void forwardRight() throws Exception{
        Direction value = Direction.toDirection(true, false, false, true);
        assertEquals(Direction.FORWARD_RIGHT, value);
    }

    @Test
    public void backLeft() throws Exception{
        Direction value = Direction.toDirection(false, true, true, false);
        assertEquals(Direction.BACK_LEFT, value);
    }

    @Test
    public void backRight() throws Exception{
        Direction value = Direction.toDirection(false, true, false, true);
        assertEquals(Direction.BACK_RIGHT, value);
    }

    @Test
    public void stopped() throws Exception{
        Direction value = Direction.toDirection(false, false, false, false);
        assertEquals(Direction.STOPPED, value);
    }

    // The more unusual cases
    @Test
    public void forwardBackward() throws Exception{
        Direction value = Direction.toDirection(true, true, false, false);
        assertEquals(Direction.STOPPED, value);
    }

    @Test
    public void leftRight() throws Exception{
        Direction value = Direction.toDirection(false, false, true, true);
        assertEquals(Direction.STOPPED, value);
    }

    @Test
    public void forwardLeftRight() throws Exception{
        Direction value = Direction.toDirection(true, false, true, true);
        assertEquals(Direction.FORWARD, value);
    }

    @Test
    public void BackwardLeftRight() throws Exception{
        Direction value = Direction.toDirection(false, true, true, true);
        assertEquals(Direction.BACKWARD, value);
    }
}
