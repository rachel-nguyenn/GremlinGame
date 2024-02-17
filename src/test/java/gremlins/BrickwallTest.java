package gremlins;

import processing.core.PApplet;
import processing.core.PImage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BrickwallTest {

    @Test
    public void constructor() {
        assertNotNull(new Brickwall(null));
    }

    @Test
    public void advanceToNextImageTest() {
        Brickwall b = new Brickwall(null);
        b.frame = 4;
        PImage[] ls = {null, null, null, null};
        b.advanceToNextImage(ls);
        assertEquals(null, b.image);

        b.frame = 1;
        b.advanceToNextImage(ls);
        assertEquals(2, b.frame);

        b.frame = 8;
        b.index = 1;
        b.advanceToNextImage(ls);
        assertEquals(2, b.index);

        b.frame = 8;
        b.index = 3;
        b.advanceToNextImage(ls);
        assertEquals(9, b.frame);

        b.frame = 18;
        b.advanceToNextImage(ls);
        assertTrue(b.animateDone);
    }


}