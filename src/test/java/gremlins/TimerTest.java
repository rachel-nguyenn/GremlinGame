package gremlins;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimerTest {

    @Test
    public void constructor() {
        assertNotNull(new Timer(10));
    }

    @Test
    public void startTest() {
        Timer t = new Timer(5);
        t.start();
        assertEquals(0, t.begin);
    }

    @Test
    public void finishTest() {
        Timer t = new Timer(5);
        t.num = 0;
        assertTrue(t.finish());
    }

    @Test
    public void getIntervalTest() {
        Timer t1 = new Timer(5);
        assertEquals(5000, t1.getInterval());
        Timer t2 = new Timer(0);
        assertEquals(0, t2.getInterval());
    }

    @Test
    public void getPassedTimeTest() {
        Timer t1 = new Timer(10);
        assertEquals(0, t1.getPassedTime());
    }

}
