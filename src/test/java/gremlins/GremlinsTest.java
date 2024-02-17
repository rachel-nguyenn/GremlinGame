package gremlins;

import processing.core.PApplet;

import org.junit.jupiter.api.Test;

import gremlins.Sprite.Move;

import static org.junit.jupiter.api.Assertions.*;

public class GremlinsTest {

    @Test
    public void constructor() {
        assertNotNull(new Gremlins(null));
    }

    @Test
    public void isOverlapTest() {
        Sprite p = new Player(null, null, null, null, null);
        p.center_x = 20;
        p.center_y = 40;

        Gremlins g1 = new Gremlins(null);
        g1.center_x = 18;
        g1.center_y = 40;
        assertTrue(g1.isOverlap(p));

        Gremlins g2 = new Gremlins(null);
        g2.center_x = 100;
        g2.center_y = 200;
        assertFalse(g2.isOverlap(p));

        Gremlins g3 = new Gremlins(null);
        g3.center_x = 50;
        g3.center_y = 47;
        assertFalse(g3.isOverlap(p));

        Gremlins g4 = new Gremlins(null);
        g4.center_x = 20;
        g4.center_y = 40;
        assertTrue(g4.isOverlap(p));
    }

    @Test
    public void getOppositeDirectionTest() {
        Gremlins g1 = new Gremlins(null);
        g1.center_x = 18;
        g1.center_y = 40;
        assertEquals(Move.UP, g1.getOppositeDirection(Move.DOWN));
        assertEquals(Move.DOWN, g1.getOppositeDirection(Move.UP));
        assertEquals(Move.LEFT, g1.getOppositeDirection(Move.RIGHT));
        assertEquals(Move.RIGHT, g1.getOppositeDirection(Move.LEFT));
    }



}