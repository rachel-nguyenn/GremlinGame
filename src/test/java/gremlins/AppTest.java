package gremlins;

import processing.core.PApplet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class AppTest {
    @Test
    public void setupTest() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000); 
    }

    @Test
    public void drawTest () {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.draw();
        app.delay(1000); 
    }

    @Test
    public void checkCollisionTest() {
        App app = new App();
        Player witch = new Player(null, null, null, null, null);
        witch.center_x = 40;
        witch.center_y = 50;

        Gremlins gremlin1 = new Gremlins(null);
        gremlin1.center_x = 45;
        gremlin1.center_y = 50;
        assertTrue(app.checkCollision(witch, gremlin1));

        Gremlins gremlin2 = new Gremlins(null);
        gremlin2.center_x = 37;
        gremlin2.center_y = 50;
        assertTrue(app.checkCollision(witch, gremlin2));

        Gremlins gremlin3 = new Gremlins(null);
        gremlin3.center_x = 90;
        gremlin3.center_y = 80;
        assertFalse(app.checkCollision(witch, gremlin3));

        Gremlins gremlin4 = new Gremlins(null);
        gremlin4.center_x = 40;
        gremlin4.center_y = 56;
        assertTrue(app.checkCollision(witch, gremlin4));

        Gremlins gremlin5 = new Gremlins(null);
        gremlin5.center_x = 40;
        gremlin5.center_y = 47;
        assertTrue(app.checkCollision(witch, gremlin5));

        Gremlins gremlin6 = new Gremlins(null);
        gremlin6.center_x = 90;
        gremlin6.center_y = 120;
        assertFalse(app.checkCollision(witch, gremlin6));

        Gremlins gremlin7 = new Gremlins(null);
        gremlin7.center_x = 40;
        gremlin7.center_y = 50;
        assertTrue(app.checkCollision(witch, gremlin7));
    }

    @Test
    public void checkCollisionListTest() {
        App app = new App();
        Player witch = new Player(null, null, null, null, null);
        witch.center_x = 40;
        witch.center_y = 50;

        Gremlins gremlin1 = new Gremlins(null);
        gremlin1.center_x = 45;
        gremlin1.center_y = 50;

        Gremlins gremlin2 = new Gremlins(null);
        gremlin2.center_x = 41;
        gremlin2.center_y = 50;

        ArrayList<Sprite> ls1 = new ArrayList<>();
        ls1.add(gremlin1);
        ls1.add(gremlin2);

        assertNotNull(app.checkCollisionList(witch, ls1));

        Gremlins gremlin3 = new Gremlins(null);
        gremlin3.center_x = 100;
        gremlin3.center_y = 100;

        Gremlins gremlin4 = new Gremlins(null);
        gremlin4.center_x = 150;
        gremlin4.center_y = 150;

        ArrayList<Sprite> ls2 = new ArrayList<>();
        ls2.add(gremlin3);
        ls2.add(gremlin4);

        assertNotNull(app.checkCollisionList(witch, ls2));
    }
}

