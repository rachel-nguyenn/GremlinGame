package gremlins;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class PlayerTest {

    @Test
    public void constructor() {
        assertNotNull(new Player(null, null, null, null, null));
    }

    @Test
    public void selectDirectionTest() {
        Player witch = new Player(null, null, null, null, null);
        witch.center_x = 80;
        witch.center_y = 90;

        witch.vel_x = 0;
        witch.vel_y = 0;
        witch.selectDirection();
        assertEquals(0, witch.direction);

        witch.vel_x = 4;
        witch.vel_y = 0;
        witch.selectDirection();
        assertEquals(0, witch.direction);

        witch.vel_x = -4;
        witch.vel_y = 0;
        witch.selectDirection();
        assertEquals(180, witch.direction);

        witch.vel_x = 0;
        witch.vel_y = 6;
        witch.selectDirection();
        assertEquals(270, witch.direction);

        witch.vel_x = 0;
        witch.vel_y = -6;
        witch.selectDirection();
        assertEquals(90, witch.direction);

    }

    @Test
    public void selectCurrentImageTest() {
        Player witch = new Player(null, null, null, null, null);
        witch.center_x = 80;
        witch.center_y = 90;

        witch.vel_x = 0;
        witch.vel_y = 0;
        witch.selectDirection();
        witch.selectCurrentImage();
        assertEquals(null, witch.currentImage);
        
        witch.vel_x = 4;
        witch.vel_y = 0;
        witch.selectDirection();
        witch.selectCurrentImage();
        assertEquals(null, witch.currentImage);

        witch.vel_x = -5;
        witch.vel_y = 0;
        witch.selectDirection();
        witch.selectCurrentImage();
        assertEquals(null, witch.currentImage);

        witch.vel_x = 0;
        witch.vel_y = 8;
        witch.selectDirection();
        witch.selectCurrentImage();
        assertEquals(null, witch.currentImage);


        witch.vel_x = 0;
        witch.vel_y = -2;
        witch.selectDirection();
        witch.selectCurrentImage();
        assertEquals(null, witch.currentImage);

        witch.direction = 271;
        witch.selectCurrentImage();
        assertEquals(null, witch.currentImage);
    }

    @Test
    public void defaultSpeedTest() {
        Player witch = new Player(null, null, null, null, null);
        witch.center_x = 80;
        witch.center_y = 90;

        witch.defaultSpeed();
        assertEquals(2, witch.player_vel);
    }

    @Test
    public void changeSpeedTest() {
        Player witch = new Player(null, null, null, null, null);
        witch.center_x = 80;
        witch.center_y = 90;

        witch.changeSpeed();
        assertEquals(4, witch.player_vel);
    }

    @Test
    public void updateImageTest() {
        Player witch = new Player(null, null, null, null, null);
        witch.center_x = 80;
        witch.center_y = 90;
        witch.image = witch.currentImage;
        witch.updateImage();
        assertEquals(null, witch.image);
    }

    @Test
    public void updateTest() {
        Player witch = new Player(null, null, null, null, null);
        witch.center_x = 80;
        witch.center_y = 90;

        witch.vel_x = 0;
        witch.vel_y = 6;
        witch.update();

        assertEquals(270, witch.direction);
        assertEquals(null, witch.currentImage);
        assertEquals(null, witch.image);

    }

    @Test
    public void moveTest() {
        Player witch = new Player(null, null, null, null, null);
        witch.center_x = 70;
        witch.center_y = 90;

        witch.checkInsideTileVert();
        witch.moveRight();
        assertEquals(2, witch.player_vel);

        witch.checkInsideTileVert();
        witch.moveLeft();
        assertEquals(2, witch.player_vel);

        witch.checkInsideTileHor();
        witch.moveUp();
        assertEquals(2, witch.player_vel);

        witch.checkInsideTileHor();
        witch.moveDown();
        assertEquals(2, witch.player_vel);

    }

    @Test
    public void checkInsideTileHorTest() {
        Player witch1 = new Player(null, null, null, null, null);
        witch1.center_x = 80;
        witch1.center_y = 90;
        assertFalse(witch1.checkInsideTileHor());

        Player witch2 = new Player(null, null, null, null, null);
        witch2.center_x = 70;
        witch2.center_y = 90;
        assertTrue(witch2.checkInsideTileHor());
    }

    @Test
    public void checkInsideTileVertTest() {
        Player witch1 = new Player(null, null, null, null, null);
        witch1.center_x = 50;
        witch1.center_y = 48;
        assertFalse(witch1.checkInsideTileVert());

        Player witch2 = new Player(null, null, null, null, null);
        witch2.center_x = 50;
        witch2.center_y = 90;
        assertTrue(witch2.checkInsideTileVert());
    }
        

}
