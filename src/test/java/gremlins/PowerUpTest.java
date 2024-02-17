package gremlins;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PowerUpTest {

    @Test
    public void constructor1() {
        assertNotNull(new PowerUp(null, 10));
    }

    @Test
    public void constructor2() {
        assertNotNull(new PowerUp(null));
    }

    @Test
    public void checkAppearedTest() {
        FreezeTile f = new FreezeTile(null, 10);
        assertFalse(f.checkAppeared());
    }

    @Test
    public void canSpawnTest() {
        FreezeTile f = new FreezeTile(null, 10);
        assertFalse(f.canSpawn());
    }


}
