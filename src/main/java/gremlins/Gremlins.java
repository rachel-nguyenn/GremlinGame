package gremlins;

import processing.core.PImage;

import java.util.*;

public class Gremlins extends Sprite{

    public static final Random randomGenerator = new Random();

    public final static int GREMLIN_VEL = 1;
    public Random random;
    public Move currentDirection;


    public Gremlins(PImage image) {
        super(image);
        currentDirection = Move.UP;
    }


    public void update(ArrayList<Sprite> tiles, Sprite[][] grid) {
        move(currentDirection);

        boolean touched = false;
        for (Sprite tile : tiles) {
            if (isOverlap(tile)) { 
                move(getOppositeDirection(currentDirection));
                currentDirection = getNewDirection(grid);
                break;
           }
        }
    }

    public boolean isOverlap(Sprite s) {
        return ((this.getLeft() < s.getRight()) && (this.getRight() > s.getLeft()) &&
                (this.getTop() < s.getBottom()) && (this.getBottom() > s.getTop()));
    }

    public Move getOppositeDirection(Move dir) {
        switch(dir) {
            case LEFT:
                return Move.RIGHT;
            case RIGHT:
                return Move.LEFT;
            case UP:
                return Move.DOWN;
            case DOWN:
                return Move.UP;
        }
        return null;
    }
 
    private Move getNewDirection(Sprite[][] grid) {
        ArrayList<Move> possibleDirections = new ArrayList<Move>();

        int selfGridX = Math.round(center_x / 20)-1;
        int selfGridY = Math.round(center_y / 20)-1;
        if (grid[selfGridX-1][selfGridY] == null)
            possibleDirections.add(Move.LEFT);
        if (grid[selfGridX+1][selfGridY] == null)
            possibleDirections.add(Move.RIGHT);
        if (grid[selfGridX][selfGridY-1] == null)
            possibleDirections.add(Move.UP);
        if (grid[selfGridX][selfGridY+1] == null)
            possibleDirections.add(Move.DOWN);

        Random randomGenerator = new Random();
        if (possibleDirections.size() >= 2) {
            Iterator itr = possibleDirections.iterator();
            while (itr.hasNext()) {
                Move dir = (Move)itr.next();
                if (dir == getOppositeDirection(currentDirection)) {
                    itr.remove();
                    break;
                }   
            }
        }
        int index = randomGenerator.nextInt(possibleDirections.size());

        return possibleDirections.get(index);
    }


    public void respawn(Player p, Sprite[][] grid, Gremlins g, int rand) {

        int pGridX = Math.round(p.center_x / 20);
        int pGridY = Math.round(p.center_y / 20);

        switch(rand) {
            case 0:
                for (int col = pGridX+10; col < 36; col++) {
                    if (grid[col][pGridY] == null) {
                        g.center_x = 20/2 + col * 20;
                        g.center_y = 20/2 + pGridY * 20;
                        break;
                    }
                }
            case 1:
                for (int row = pGridY+10; row < 33; row++) {
                    if (grid[pGridX][row] == null) {
                        g.center_x = 20/2 + pGridX * 20;
                        g.center_y = 20/2 + row * 20;
                        break;
                    }
                }
        }
        

    }
}