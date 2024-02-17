package gremlins;

import processing.core.PApplet;
import processing.core.PImage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FireballTest {

    @Test
    public void constructor() {
        assertNotNull(new Fireball(null));
    }

    @Test
    public void updateTest() {
        Fireball fb = new Fireball(null);
        fb.center_x = 100;
        fb.center_y = 120;
        fb.firing = true;
        fb.direction = 0;
        fb.update();
        assertEquals(104, fb.center_x);

        fb.direction = 180;
        fb.update();
        assertEquals(100, fb.center_x);

        fb.direction = 90;
        fb.update();
        assertEquals(116, fb.center_y);

        fb.direction = 270;
        fb.update();
        assertEquals(120, fb.center_y);

        fb.firing = false;
        fb.direction = 270;
        fb.update();
        assertEquals(120, fb.center_y);

        fb.firing = true;
        fb.direction = 272;
        fb.update();
        assertEquals(120, fb.center_y);
    }

    @Test
    public void setLocationTest() {
        Fireball fb = new Fireball(null);
        fb.center_x = 90;
        fb.center_y = 70;
        fb.setLocation(40, 30);
        assertEquals(40, fb.center_x);
        assertEquals(30, fb.center_y);
    }

}
