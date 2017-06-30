/*7
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InGameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author bas25
 */
public class Goal extends InGameObject {

    private long animationTime;
    private long animationTimeBefore;
    private long animationTimePrev;
    private long animationSpeed;
    private int indicator;
    private int currentFrame;
    private BufferedImage Sprite;
    private ArrayList<BufferedImage> frames;

    public Goal(int x, int y) {
        super(x, y);
        this.obj_width = 170;
        this.obj_height = 80;
        this.indicator = 1;
        animationTime = 0;
        animationSpeed = 100;
        animationTimeBefore = 0;
        animationTimePrev = 0;
        currentFrame = 0;
        Sprite = null;
        this.frames = new ArrayList<>();
        this.loadImage();
    }

    public void animation() {
        animationTime = System.currentTimeMillis();

        if (animationTime - animationTimePrev >= animationSpeed) {
            currentFrame += indicator;
            if (currentFrame >= 10) {
                indicator = -1;  // Backward the sprite images.
            }
            if (currentFrame <= 0) {
                indicator = 1;   // Forward the sprite images.
            }
            Sprite = frames.get(currentFrame);
            animationTimePrev = animationTime;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Sprite, x, y, null);
        this.animation();
    }

    @Override
    public void move() {
        // Don't support yet.
    }

    @Override
    public void loadImage() {
        URL url = this.getClass().getResource("goal_Sheet.png");
        try {
            Sprite = ImageIO.read(url);
        } catch (IOException ex) {
            System.out.println("no image");
        }

        //Row1 (0-5)// 
        frames.add(Sprite.getSubimage(0, 0, this.obj_width, this.obj_height));
        frames.add(Sprite.getSubimage(171, 0, this.obj_width, this.obj_height));
        frames.add(Sprite.getSubimage(342, 0, this.obj_width, this.obj_height));
        frames.add(Sprite.getSubimage(514, 0, this.obj_width, this.obj_height));
        frames.add(Sprite.getSubimage(686, 0, this.obj_width, this.obj_height));
        frames.add(Sprite.getSubimage(858, 0, this.obj_width, this.obj_height));

        //Row2 (6-10)//
        frames.add(Sprite.getSubimage(0, 90, this.obj_width, this.obj_height));
        frames.add(Sprite.getSubimage(171, 90, this.obj_width, this.obj_height));
        frames.add(Sprite.getSubimage(342, 90, this.obj_width, this.obj_height));
        frames.add(Sprite.getSubimage(514, 90, this.obj_width, this.obj_height));
        frames.add(Sprite.getSubimage(686, 90, this.obj_width, this.obj_height));
        
    }
}
