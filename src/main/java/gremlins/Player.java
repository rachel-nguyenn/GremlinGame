package gremlins;

import java.util.*;

import processing.core.PImage;

public class Player extends Sprite{

    public int direction;
    public PImage currentImage;
    private PImage standLeft;
    private PImage standRight;
    private PImage standUp;
    private PImage standDown;
    public int player_vel;

    public ArrayList<Sprite> shots;
    public Fireball fb;

    public Player(PImage image, PImage imageLeft, PImage imageRight, PImage imageUp, PImage imageDown) {
        super(image);
        direction = RIGHT_FACING;
        currentImage = imageRight;
        standLeft = imageLeft;
        standRight = imageRight;
        standUp = imageUp;
        standDown = imageDown;
        player_vel = 2;
    }

    public void update() {
        selectDirection();
        selectCurrentImage();
        updateImage();
    }

    public void moveLeft() {
        this.vel_x = -player_vel;
        if (checkInsideTileVert()) {
            this.vel_y = 0;
        }
    }

    public void moveRight() {
        this.vel_x = player_vel;
        if (checkInsideTileVert()) {
            this.vel_y = 0;
        }
    }

    public void moveUp() {
        this.vel_y = -player_vel;
        if (checkInsideTileHor()) {
            this.vel_x = 0;
        }
    }

    public void moveDown() {
        this.vel_y = player_vel;
        if (checkInsideTileHor()) {
            this.vel_x = 0;
        }
    }

    public void changeSpeed() {
        player_vel = 4;
    }

    public void defaultSpeed() {
        player_vel = 2;
    }

    public void selectDirection() {
        if (vel_x > 0) {
            direction = RIGHT_FACING;
        }
        else if (vel_x < 0) {
            direction = LEFT_FACING;
        }
        else if (vel_y > 0){
            direction = DOWN_FACING;
        }
        else if (vel_y < 0){
            direction = UP_FACING;
        }
    }

    public void selectCurrentImage() {
        if (direction == RIGHT_FACING) {
            currentImage = standRight;
        }
        else if (direction == LEFT_FACING) {
            currentImage = standLeft;
        }
        else if (direction == DOWN_FACING) {
            currentImage = standDown;
        }
        else if (direction == UP_FACING){
            currentImage = standUp;
        }
    }

    public void updateImage() {
        image = currentImage;
    }

    public boolean checkInsideTileHor() {
        boolean insideTileX = (getRight() % 20 == 0);
        return insideTileX;
    }

    public boolean checkInsideTileVert() {
        boolean insideTileY = (getBottom() % 20 == 0);
        return insideTileY;
    }



}