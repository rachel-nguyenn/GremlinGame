package gremlins;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FreezeTileTest {

    @Test
    public void constructor() {
        assertNotNull(new FreezeTile(null, 10));
    }

}
