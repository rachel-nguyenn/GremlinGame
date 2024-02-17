package gremlins;

import processing.core.PImage;

public class PowerUp extends Sprite {

    public Timer timerSpawn;
    public Timer timerLast;
    public boolean appeared = false;

    public PowerUp(PImage image, double timeSpawn) {
        super(image);
        timerLast = new Timer(10);
        timerLast.setIndex();
        timerSpawn = new Timer(timeSpawn);
        timerSpawn.setIndex();
        timerSpawn.start();
    }

    public PowerUp(PImage image) {
        super(image);
    }

    public boolean checkAppeared() {
        return this.appeared;
    }

    public boolean canSpawn() {
        if (timerSpawn.finish()) {
            this.appeared = true;
            return true;
        } else {
            return false;
        }
    }
}
