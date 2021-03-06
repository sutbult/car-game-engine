package se.chalmers.tda367_4.game;

import org.junit.Test;
import se.chalmers.tda367_4.geometry.color.ApplicationColor;
import se.chalmers.tda367_4.geometry.vector.Vector2;

import static org.junit.Assert.*;


public class GameTextTest {
    @Test
    public void getText() throws Exception {
        GameText text = new GameText("Test", "Sans_serif", new Vector2(0,0),
                5, false, new ApplicationColor(0,0,0));
        assertTrue(text.getText() == "Test");
    }

    @Test
    public void getFont() throws Exception {
        GameText text1 = new GameText("Test", "Sans_serif", new Vector2(0,0),
                5, false, new ApplicationColor(0,0,0));
        GameText text2 = new GameText("Test", "Serif", new Vector2(0,0),
                5, false, new ApplicationColor(0,0,0));

        assertEquals("Sans_serif", text1.getFont());
        assertEquals("Serif", text2.getFont());
        assertFalse(text1.getFont() == text2.getFont());
    }

    @Test
    public void getPosition() throws Exception {
        GameText text = new GameText("Test", "Sans_serif", new Vector2(0,0),
                5, false, new ApplicationColor(0,0,0));
        Vector2 testVector = new Vector2(0, 0);

        assertEquals(testVector, text.getPosition());
    }

    @Test
    public void getHeight() throws Exception {
        GameText text = new GameText("Test", "Sans_serif", new Vector2(0,0),
                5, false, new ApplicationColor(0,0,0));
        assertTrue(text.getHeight() == 5);
    }

    @Test
    public void isBold() throws Exception {
        GameText text1 = new GameText("Test", "Sans_serif", new Vector2(0,0),
                5, false, new ApplicationColor(0,0,0));
        GameText text2 = new GameText("Test", "Sans_serif", new Vector2(0,0),
                5, true, new ApplicationColor(0,0,0));

        assertFalse(text1.isBold());
        assertTrue(text2.isBold());
    }

    @Test
    public void getColor() throws Exception {
        GameText text1 = new GameText("Test1", "Sans_serif", new Vector2(0,0),
                5, false, new ApplicationColor(25,50,100));
        GameText text2 = new GameText("Test2", "Serif", new Vector2(1,1),
                5, false, new ApplicationColor(25,50,100));

        assertEquals(text1.getColor().getR(), text2.getColor().getR());
        assertEquals(text1.getColor().getG(), text2.getColor().getG());
        assertEquals(text1.getColor().getB(), text2.getColor().getB());
    }

}