package gremlins;

import org.junit.jupiter.api.Test;

import gremlins.Sprite.Move;

import static org.junit.jupiter.api.Assertions.*;

public class SlimeTest {

    @Test
    public void constructor() {
        assertNotNull(new Slime(null));
    }

    @Test
    public void updateTest() {
        Slime s = new Slime(null);
        s.center_x = 20;
        s.center_y = 10;

        s.throwSlime = false;
        s.update();
        assertEquals(20, s.center_x);

        s.throwSlime = true;
        s.direction = Move.RIGHT;
        s.update();
        assertEquals(24, s.center_x);

        s.direction = Move.LEFT;
        s.update();
        assertEquals(20, s.center_x);

        s.direction = Move.UP;
        s.update();
        assertEquals(6, s.center_y);

        s.direction = Move.DOWN;
        s.update();
        assertEquals(10, s.center_y);
    }

    @Test
    public void setLocationSlimeTest() {
        Slime s = new Slime(null);
        s.center_x = 80;
        s.center_y = 85;

        s.setLocationSlime(0, 0);
        assertEquals(0, s.center_x);
        assertEquals(0, s.center_y);

        s.setLocationSlime(20, 50);
        assertEquals(20, s.center_x);
        assertEquals(50, s.center_y);
    }

}