package gremlins;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;

import java.util.*;
import java.io.*;


public class App extends PApplet {

    public static final int WIDTH = 720;
    public static final int HEIGHT = 720;
    public static final int SPRITESIZE = 20;
    public static final int BOTTOMBAR = 60;
    public static final int FPS = 60;

    public static final Random randomGenerator = new Random();

    public String configPath;
    
    public PImage brickwall;
    public PImage stonewall;
    public PImage gremlin;
    public PImage slime;
    public PImage door;
    public PImage fireball;
    public PImage icy;
    public PImage power;

    public PImage witchRight;
    public PImage witchLeft;
    public PImage witchDown;
    public PImage witchUp;

    public PImage destroy0;
    public PImage destroy1;
    public PImage destroy2;
    public PImage destroy3;

    public String filename;
    public float wizardCool;
    public float enemyCool;

    public boolean continue_move_x;
    public boolean continue_move_y;
    public boolean checkFreezeCollided;
    public boolean checkLightningCollided;
    public boolean space;
    public boolean gameOver;
    public boolean win;
    public int round = 1;
    public int per = 0;
    public int lives;
    public int livesLost = 0;
    public int appear;

    int begin; 
    int duration = 5;
    int time = 5;

    int start; 
    int interval = 5;
    int tic = 5;

    public ArrayList<Sprite> platform;
    public Sprite[][] platformGrid;
    public ArrayList<Sprite> brick;
    public ArrayList<Sprite> stone;
    public ArrayList<Gremlins> enemies;
    public ArrayList<Fireball> shots;
    public ArrayList<Brickwall> destroyed;
    public ArrayList<Slime> shotSlime;
    public ArrayList<Sprite> lightning;
    public ArrayList<Sprite> freezing;
    public ArrayList<FreezeTile> freezingInEffective;
    public ArrayList<PowerTile> lightningInEffective;
    public PImage[] destWall;
    public PImage[] witchImg;

    public Config cf;
    public Fireball fb;
    public Player player;
    public Slime sl;
    public Door exit;
    public Timer fireballTimer;


    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        frameRate(FPS);
        imageMode(CENTER);
        gameOver = false;
        win = false;

        // Load images during setup
        this.stonewall = loadImage(this.getClass().getResource("resources/stonewall.png").getPath().replace("%20", " "));
        this.brickwall = loadImage(this.getClass().getResource("resources/brickwall.png").getPath().replace("%20", " "));
        this.gremlin = loadImage(this.getClass().getResource("resources/gremlin.png").getPath().replace("%20", " "));
        this.door = loadImage(this.getClass().getResource("resources/exit.png").getPath().replace("%20", " "));
        this.slime = loadImage(this.getClass().getResource("resources/slime.png").getPath().replace("%20", " "));
        this.fireball = loadImage(this.getClass().getResource("resources/fireball.png").getPath().replace("%20", " "));
        this.icy = loadImage(this.getClass().getResource("resources/freeze.png").getPath().replace("%20", " "));
        this.power = loadImage(this.getClass().getResource("resources/power.png").getPath().replace("%20", " "));

        this.witchLeft = loadImage(this.getClass().getResource("resources/wizard0.png").getPath());
        this.witchRight = loadImage(this.getClass().getResource("resources/wizard1.png").getPath());
        this.witchUp = loadImage(this.getClass().getResource("resources/wizard2.png").getPath());
        this.witchDown = loadImage(this.getClass().getResource("resources/wizard3.png").getPath());

        this.destroy0 = loadImage(this.getClass().getResource("resources/brickwall_destroyed0.png").getPath());
        this.destroy1 = loadImage(this.getClass().getResource("resources/brickwall_destroyed1.png").getPath());
        this.destroy2 = loadImage(this.getClass().getResource("resources/brickwall_destroyed2.png").getPath());
        this.destroy3 = loadImage(this.getClass().getResource("resources/brickwall_destroyed3.png").getPath());

        // Config.json
        JSONObject conf = loadJSONObject(new File(this.configPath));
        lives = conf.getInt("lives");
        lives -= livesLost;
        cf = new Config(conf);
        HashMap<Integer, ArrayList<Object>> data = cf.extract();

        platform = new ArrayList<Sprite>();
        brick = new ArrayList<Sprite>();
        stone = new ArrayList<Sprite>();
        enemies = new ArrayList<Gremlins>();
        shots = new ArrayList<Fireball>();
        destroyed = new ArrayList<Brickwall>();
        shotSlime = new ArrayList<Slime>();
        lightning = new ArrayList<Sprite>();
        freezing = new ArrayList<Sprite>();
        lightningInEffective = new ArrayList<PowerTile>();
        freezingInEffective = new ArrayList<FreezeTile>();
        platformGrid = new Sprite[36][33];

        exit = new Door(door);
        player = new Player(witchRight, witchLeft, witchRight, witchUp, witchDown);
        player.vel_x = 0;
        player.vel_y = 0;

        //space = false;
        destWall = new PImage[4];
        destWall[0] = destroy0;
        destWall[1] = destroy1;
        destWall[2] = destroy2;
        destWall[3] = destroy3;

        filename = (String) (data.get(round)).get(0);
        wizardCool = (float) (data.get(round)).get(1);
        enemyCool = (float) (data.get(round)).get(2);

        fireballTimer = new Timer(wizardCool);
        createMap(filename);

    }


    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(){
        if (gameOver) {
            gameOver = false;
            livesLost = 0;
            round = 1;
            setup();
        }
        else {
            if (keyCode == RIGHT) {
                player.moveRight();
            }
            else if (keyCode == LEFT) {
                player.moveLeft();
            }
            else if (keyCode == UP) {
                player.moveUp();
            }
            else if (keyCode == DOWN) { 
                player.moveDown();
            }
            else if (keyCode == 32) {
                if (fireballTimer.finish()) {
                    //Shotting
                    space = true;
                    fb = new Fireball(fireball);
                    fb.firing = true;
                    fb.setLocation(player.center_x, player.center_y);
                    fb.direction = player.direction;
                    shots.add(fb);
                    fireballTimer.start();
                }
            }
        }
    }
    
    /**
     * Receive key released signal from the keyboard.
    */
    public void keyReleased(){
        if (keyCode != 32) {
            if (keyCode == RIGHT) {
                if (player.checkInsideTileHor()) {
                    player.vel_x = 0;
                } else {
                    continue_move_x = true;
                }
            }
            else if (keyCode == LEFT) {
                if (player.checkInsideTileHor()) {
                    player.vel_x = 0;
                } else {
                    continue_move_x = true;
                }
            }
            else if (keyCode == UP) {
                if (player.checkInsideTileVert()) {
                    player.vel_y = 0;
                } else {
                    continue_move_y = true;
                }
            }
            else if (keyCode == DOWN) {
                if (player.checkInsideTileVert()) {
                    player.vel_y = 0;
                } else {
                    continue_move_y = true;
                }
            }
        }
    }


    /**
     * Draw all elements in the game by current frame. 
	 */
    public void draw() {

        if (gameOver) {
            displayGameState();
        }
        else {
            background(186, 153, 120);
            textSize(18);
            fill(255);
            text("Lives: ",10,HEIGHT-25);
            text("Level " + round + "/2",200,HEIGHT-25);
    
            livesDisplay();

            for (Sprite s : platform) { 
                s.draw(this);
            }
    
            for (Fireball bullet : shots) {
                bullet.update();
                bullet.draw(this);
            }
    
            fireballCollision();
    
            slimeCollision();
            
            //playerCollision(curLives);
    
            player.update();
            player.draw(this);
            resolveMapCollision(player, platform);
            exit.draw(this);
    
            powerChange();
    
            checkMoveWholeTile();
    
            //Progress bar at right bottom corner
            cooldownBar();
            
            
            for (Slime slimey : shotSlime) {
                slimey.update();
                slimey.draw(this);
            }
            
            displayPower();
            displayFreeze();
            displayGremlins();

            doorCollision();
            incrementLives();
        }

    }

    
    public void createMap(String f) {
        String[] lines = loadStrings(f);
        for (int row = 0; row < lines.length; row++) {
            String[] values = lines[row].split("");
            for (int col = 0; col < values.length; col++) {
                if (values[col].equals("B")) {
                    Sprite map = new Brickwall(brickwall);
                    map.center_x = SPRITESIZE/2 + col * SPRITESIZE;
                    map.center_y = SPRITESIZE/2 + row * SPRITESIZE;
                    platform.add(map);
                    platformGrid[col][row] = map;
                    brick.add(map);
                }
                else if (values[col].equals("X")) {
                    Sprite map = new Stonewall(stonewall);
                    map.center_x = SPRITESIZE/2 + col * SPRITESIZE;
                    map.center_y = SPRITESIZE/2 + row * SPRITESIZE; 
                    platform.add(map);
                    platformGrid[col][row] = map;
                    stone.add(map);
                }
                else if (values[col].equals("E")) {
                    exit.center_x = SPRITESIZE/2 + col * SPRITESIZE;
                    exit.center_y = SPRITESIZE/2 + row * SPRITESIZE;
                }
                else if (values[col].equals("G")) {
                    Gremlins map = new Gremlins(gremlin);
                    map.center_x = SPRITESIZE/2 + col * SPRITESIZE;
                    map.center_y = SPRITESIZE/2 + row * SPRITESIZE; 
                    enemies.add(map);
                }
                else if (values[col].equals("F")) {
                    FreezeTile map = new FreezeTile(icy, 10);
                    map.center_x = SPRITESIZE/2 + col * SPRITESIZE;
                    map.center_y = SPRITESIZE/2 + row * SPRITESIZE; 
                    freezing.add(map);
                }
                else if (values[col].equals("P")) {
                    PowerTile map = new PowerTile(power, 10);
                    map.center_x = SPRITESIZE/2 + col * SPRITESIZE;
                    map.center_y = SPRITESIZE/2 + row * SPRITESIZE; 
                    lightning.add(map);
                }
                else if (values[col].equals("W")) {
                    player.center_x = SPRITESIZE/2 + col * SPRITESIZE;
                    player.center_y = SPRITESIZE/2 + row * SPRITESIZE; 
                }
            }
        }
    }

    //Check if player is inside a whole tile, not part way between tiles
    public void checkMoveWholeTile() {
        if (continue_move_x) {
            if (player.checkInsideTileHor()) {
                continue_move_x = false;
                player.vel_x = 0;
            }
        }
        if (continue_move_y) {
            if (player.checkInsideTileVert()) {
                continue_move_y = false;
                player.vel_y = 0;
            }
        }
    }

    //Cooldown Bar at right bottom corner
    public void cooldownBar() {
        if (space) {
            if (!fireballTimer.finish()) {
                image(fireball, 580, HEIGHT-30);
                double percent = (fireballTimer.getPassedTime() / fireballTimer.getInterval()) * 80;
                fill(255);
                rect(600, HEIGHT-35, 80, 5);
                fill(0);
                rect(600, HEIGHT-35, (int) percent, 5);
            }
        }
    }

    //PowerUp - SpeedUp
    public void displayPower() {
        for (int i = 0; i < lightning.size(); i++) {
            PowerTile p = (PowerTile) lightning.get(i);
            if (p.checkAppeared()) {
                p.draw(this);
                checkCollidewithPower(p);
            } else {
                if (p.canSpawn()) {
                    p.draw(this);
                } 
            }
        }

        for (int j = 0; j < lightningInEffective.size(); j++) {
            PowerTile thunder = (PowerTile) lightningInEffective.get(j);
            if (thunder.timerLast.finish()) {
                lightningInEffective.remove(lightningInEffective.get(j));
            }
            else {
                double per = (thunder.timerLast.getPassedTime() / thunder.timerLast.getInterval()) * 80;

                fill(255);
                textSize(15);
                text("Time Left", 375, 680);

                fill(255);
                rect(370, 690, 80, 5);

                fill(235, 184, 16);
                noStroke();
                rect(370, 690, (int) per, 5);
            }
        }

        if (lightningInEffective.size() > 0) {
            checkLightningCollided = true;
        } else {
            checkLightningCollided = false;
        }

    }

    public void checkCollidewithPower(PowerTile p) {
        if (checkCollision(player, p)) {
            checkLightningCollided = true;
            p.timerLast.start();
            lightningInEffective.add(p);
            int randomRespawn = randomGenerator.nextInt(10) + 1;
            PowerTile renewPower = new PowerTile(power, randomRespawn);
            renewPower.center_x = p.center_x;
            renewPower.center_y = p.center_y;
            lightning.add(renewPower);
            lightning.remove(p);
        }
    }

    public void powerChange() {
        if (checkLightningCollided) {
            player.changeSpeed();
        } 
        else {
            player.defaultSpeed();
        }
    }


    //Extension - Freeze the enemies
    public void displayFreeze() {
        for (int i = 0; i < freezing.size(); i++) {
            FreezeTile f = (FreezeTile) freezing.get(i);
            if (f.checkAppeared()) {
                f.draw(this);
                checkCollidewithFreeze(f);
            } else {
                if (f.canSpawn()) {
                    f.draw(this);
                } 
            }
        }

        for (int j = 0; j < freezingInEffective.size(); j++) {
            FreezeTile fr = (FreezeTile) freezingInEffective.get(j);
            if (fr.timerLast.finish()) {
                freezingInEffective.remove(freezingInEffective.get(j));
            }
            else {
                double per = (fr.timerLast.getPassedTime() / fr.timerLast.getInterval()) * 80;

                fill(255);
                textSize(15);
                text("Time Left", 375, 680);

                fill(255);
                rect(370, 690, 80, 5);

                fill(16, 136, 235);
                noStroke();
                rect(370, 690, (int) per, 5);
            }
        }

        if (freezingInEffective.size() > 0) {
            checkFreezeCollided = true;
        } else {
            checkFreezeCollided = false;
        }
    }

    public void checkCollidewithFreeze(FreezeTile f) {
        if (checkCollision(player, f)) {
            checkFreezeCollided = true;
            f.timerLast.start();
            freezingInEffective.add(f);
            int randomRespawn = randomGenerator.nextInt(10) + 1;
            FreezeTile renewFreeze = new FreezeTile(icy, randomRespawn);
            renewFreeze.center_x = f.center_x;
            renewFreeze.center_y = f.center_y;
            freezing.add(renewFreeze);
            freezing.remove(f);
        }
    }

    public void displayGremlins() {
        for (Gremlins grem : enemies) {
            grem.draw(this);
            if (checkFreezeCollided == false) {
                grem.update(platform, platformGrid);
                if (frameCount % (enemyCool * FPS) == 0) {
                    Slime sl = new Slime(slime);
                    sl.throwSlime = true;
                    sl.setLocationSlime(grem.center_x, grem.center_y);
                    sl.direction = grem.currentDirection;
                    shotSlime.add(sl);
                }
            }
        }
    }


    public void fireballCollision() {
        //Fireball vs Slime
        for (int i = 0; i < shots.size(); i++) {
            Fireball shot = (Fireball)shots.get(i);
            for (int j = 0; j < shotSlime.size(); j++) {
                Slime slimey = (Slime) shotSlime.get(j);
                if (checkCollision(shot, slimey)) {                    
                    shots.remove(i);
                    shotSlime.remove(j);
                }
            }
        }

        //Fireball vs Stonewall
        for (int i = 0; i < shots.size(); i++) {
            Fireball shot = (Fireball)shots.get(i);
            for (int j = 0; j < stone.size(); j++) {
                Sprite tile = (Sprite) stone.get(j);
                if (checkCollision(shot, tile)) {                    
                    shots.remove(i);
                }
            }
        }


        //Fireball vs Brickwall
        for (int i = 0; i < shots.size(); i++) {
            Fireball shot = (Fireball)shots.get(i);
            for (int j = 0; j < brick.size(); j++) {
                Brickwall br = (Brickwall) brick.get(j);
                if (checkCollision(shot, br)) {   
                    shots.remove(i);
                    destroyed.add(br);
                    for (int m = 0; m < platformGrid.length; m++) {
                        for (int n = 0; n < platformGrid[m].length; n++) {
                            if (platformGrid[m][n] == br) {
                                platformGrid[m][n] = null;
                            }
                        }
                    }
                }
            }
        }


        for (int i = 0; i < destroyed.size(); i++) {
            destroyed.get(i).advanceToNextImage(destWall);
            destroyed.get(i).draw(this);
            if (destroyed.get(i).animateDone) {
                platform.remove(destroyed.get(i));
                brick.remove(destroyed.get(i));
                destroyed.remove(destroyed.get(i));
            }
        }

        //Respawn after collision between Fireball and Gremlins
        for (int i = 0; i < shots.size(); i++) {
            Fireball shot = (Fireball)shots.get(i);
            for (int j = 0; j < enemies.size(); j++) {
                Gremlins g = (Gremlins) enemies.get(j);
                if (checkCollision(shot, g)) {                    
                    shots.remove(shot);                    
                    g.respawn(player, platformGrid, g, randomGenerator.nextInt(2));
                }
            }
        }
    }


    public void slimeCollision() {
        //Slime vs Wall
        for (int i = 0; i < shotSlime.size(); i++) {
            Slime s = (Slime) shotSlime.get(i);
            for (int j = 0; j < platform.size(); j++) {
                Sprite tile = (Sprite) platform.get(j);
                if (checkCollision(s, tile)) {                    
                    shotSlime.remove(i);
                }
            }
        }
    }

    public void doorCollision() {
        boolean checkCollideExit = checkCollision(player, exit);
        if (checkCollideExit) {
            round++;
            if (round > 2) {
                gameOver = true;
                win = true;
            } 
            else {
                setup();
            }
        }
    }

    public void incrementLives() {
        ArrayList<Sprite> enemiesCollided = new ArrayList<Sprite>();
        enemiesCollided.addAll(enemies);
        enemiesCollided.addAll(shotSlime);
        ArrayList<Sprite> collided_ls = checkCollisionList(player, enemiesCollided);
        if (collided_ls.size() > 0) {
            lives--;
            if (lives == 0) {
                gameOver = true;
                win = false;
            } else {
                livesLost++;
                setup();
            }
        }
    }
    
    public void livesDisplay() {
        int x = 70;
        for (int i = 0; i < lives; i++) {
            image(witchRight, x, HEIGHT-30);
            x += 20;
        }
    }

    public void displayGameState() {

        background(186, 153, 120);
        fill(255);
        textSize(60);
        if (win) {
            text("YOU WIN!",WIDTH/2-150,HEIGHT/2);
        } 
        else {
            text("GAME OVER!",WIDTH/2-200,HEIGHT/2);
        }
    }
    
    
    public boolean checkCollision(Sprite s1, Sprite s2) {

        boolean noXoverlap = s1.getRight() <= s2.getLeft() || s1.getLeft() >= s2.getRight();
        boolean noYoverlap = s1.getBottom() <= s2.getTop() || s1.getTop() >= s2.getBottom();

        if (noXoverlap || noYoverlap) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Sprite> checkCollisionList (Sprite s, ArrayList<Sprite> list) {
        ArrayList<Sprite> collisionList = new ArrayList<Sprite>();
        for (Sprite obj : list) {
            if (checkCollision(s, obj)) {
                collisionList.add(obj);
            }
        }
        return collisionList;
    }


    public void resolveMapCollision(Sprite s, ArrayList<Sprite> walls) {
        s.center_y += s.vel_y;
        ArrayList<Sprite> col_list = checkCollisionList(s, walls);
        if (col_list.size() > 0) {
            Sprite collided = col_list.get(0);
            if (s.vel_y > 0) {
                s.setBottom(collided.getTop());
            }
            else if (s.vel_y < 0) {
                s.setTop(collided.getBottom());
            }
            s.vel_y = 0;
        }

        s.center_x += s.vel_x;
        col_list = checkCollisionList(s, walls);
        if (col_list.size() > 0) {
            Sprite collided = col_list.get(0);
            if (s.vel_x > 0) {
                s.setRight(collided.getLeft());
            }
            else if (s.vel_x < 0) {
                s.setLeft(collided.getRight());
            }
            s.vel_x = 0;
        }

    }

    public static void main(String[] args) {
        PApplet.main("gremlins.App");
    }
}