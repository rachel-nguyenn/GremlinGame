package gremlins;

import processing.core.PImage;
import processing.core.PApplet;


public class Sprite extends PApplet{

    public static final int RIGHT_FACING = 0;
    public static final int LEFT_FACING = 180;
    public static final int UP_FACING = 90;
    public static final int DOWN_FACING = 270;

    public static final int SPRITESIZE = 20;
    public PImage image;
    public float center_x;
    public float center_y;
    public float vel_x;
    public float vel_y;
    public boolean animateDone;
    public int index = 0;

    public Sprite(PImage image) {
        this.image = image;
        center_x = 0;
        center_y = 0;
        vel_x = 0;
        vel_y = 0;
    }

    public void draw(App app) {
        app.image(image, center_x, center_y, SPRITESIZE, SPRITESIZE);
    }

    public float getLeft() {
        return center_x - SPRITESIZE/2;
    }
    public void setLeft(float newLeft) {
        center_x = newLeft + SPRITESIZE/2;
    }

    public float getRight() {
        return center_x + SPRITESIZE/2;
    }
    public void setRight(float newRight) {
        center_x = newRight - SPRITESIZE/2;
    }

    public float getBottom() {
        return center_y + SPRITESIZE/2;
    }
    public void setBottom(float newBottom) {
        center_y = newBottom - SPRITESIZE/2;
    }

    public float getTop() {
        return center_y - SPRITESIZE/2;
    }
    public void setTop(float newTop) {
        center_y = newTop + SPRITESIZE/2;
    }

    public enum Move {

        DOWN(0, 1),
        UP(0, -1), 
        RIGHT(1, 0),
        LEFT(-1, 0);

        private final int deltaX;
        private final int deltaY;

        Move(final int deltaX, final int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
	    }
    }

    public void move(Move move) {
        center_y += move.deltaY;
        center_x += move.deltaX;
    }

}