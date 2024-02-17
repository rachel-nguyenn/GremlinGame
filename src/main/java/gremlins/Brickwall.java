package gremlins;

import processing.core.PImage;

public class Brickwall extends Sprite{
    public int index;
    public int frame = 0;
    
    public Brickwall(PImage image) {
        super(image);
        index = 0;
    }


    public void advanceToNextImage(PImage[] currentImage) {
        if (frame >= 16) {
            this.animateDone = true;
        } 
        else {
            if (frame % 4 == 0) {
                this.image = currentImage[index];
                if (index < 3) {
                    index++;
                }
            }
            frame++;
        }
        
    }

    
}
