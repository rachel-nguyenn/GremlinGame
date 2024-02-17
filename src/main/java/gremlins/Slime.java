package gremlins;

import processing.core.PImage;


public class Slime extends Sprite {

    public final static int SLIME_VEL = 4;
    public Move direction;
    public boolean throwSlime;

    public Slime(PImage image) {
        super(image);
        vel_x = SLIME_VEL;
        vel_y = SLIME_VEL;
        direction = Move.UP;
        throwSlime = false;
    }

    public void update() {
        if (throwSlime == true) {
            if (direction == Move.RIGHT) {
                this.center_x += SLIME_VEL;
            }
            else if (direction == Move.LEFT) {
                this.center_x -= SLIME_VEL;
            }
            else if (direction == Move.UP) {
                this.center_y -= SLIME_VEL;
            }
            else if (direction == Move.DOWN) {
                this.center_y += SLIME_VEL;
            }
        }
    }

    public void setLocationSlime(float startX, float startY) {
        center_x = startX;
        center_y = startY;
    }

}
