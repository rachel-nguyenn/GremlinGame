package gremlins;

import processing.core.PImage;

public class Fireball extends Sprite{

    public int direction;
    public final static int FIREBALL_VEL = 4;
    public boolean firing;
    
    public Fireball(PImage image) {
        super(image);
        firing = false;
        direction = RIGHT_FACING;
    }

    public void update() {
        if (firing == true) {
            if (direction == RIGHT_FACING) {
                this.center_x += FIREBALL_VEL;
            }
            else if (direction == LEFT_FACING) {
                this.center_x -= FIREBALL_VEL;
            }
            else if (direction == UP_FACING) {
                this.center_y -= FIREBALL_VEL;
            }
            else if (direction == DOWN_FACING) {
                this.center_y += FIREBALL_VEL;
            }
        }
    }

    public void setLocation(float startX, float startY) {
        center_x = startX;
        center_y = startY;
        firing = true;
    }

    
 
}
