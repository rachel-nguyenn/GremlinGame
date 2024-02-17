package gremlins;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;

import gremlins.Sprite.Move;

import static org.junit.jupiter.api.Assertions.*;

public class SpriteTest {

    @Test
    public void constructor() {
        assertNotNull(new Sprite(null));
    }

    @Test
    public void setLeftTest() {
        Player witch = new Player(null, null, null, null, null);
        witch.center_x = 80;
        witch.center_y = 90;

        witch.setLeft(100);
        assertEquals(110, witch.center_x);
    }

    @Test
    public void setRightTest() {
        Player witch = new Player(null, null, null, null, null);
        witch.center_x = 40;
        witch.center_y = 10;

        witch.setRight(80);
        assertEquals(70, witch.center_x);
    }

    @Test
    public void setTopTest() {
        Player witch = new Player(null, null, null, null, null);
        witch.center_x = 30;
        witch.center_y = 70;

        witch.setTop(30);
        assertEquals(40, witch.center_y);
    }

    @Test
    public void setBottomTest() {
        Player witch = new Player(null, null, null, null, null);
        witch.center_x = 10;
        witch.center_y = 120;

        witch.setBottom(180);
        assertEquals(170, witch.center_y);
    }


    @Test
    public void moveTest() {
        Gremlins grem = new Gremlins(null);
        grem.center_x = 80;
        grem.center_y = 90;
        grem.move(Move.RIGHT);
        assertEquals(81, grem.center_x);
        grem.move(Move.LEFT);
        assertEquals(80, grem.center_x);
        grem.move(Move.UP);
        assertEquals(89, grem.center_y);
        grem.move(Move.DOWN);
        assertEquals(90, grem.center_y);
    }

}
