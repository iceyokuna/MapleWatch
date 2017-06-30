/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InGameObjects;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import Map.Tile;
import Map.Map;
import java.awt.Rectangle;
import static maplewatch.Game.Team_A;
import static maplewatch.Game.Team_B;
import static maplewatch.Game.currentTeam;
import static maplewatch.Game.goal;
import java.awt.Color;
import maplewatch.Game;
import static maplewatch.Game.map;

/**
 *
 * @author Sivut
 */
public class Payload extends InGameObject {

    public boolean reach;
    private long animationTime;
    private long animationTimeBefore;
    private long animationTimePrev;
    private long animationSpeed;

    private int currentFrame;
    private BufferedImage Sprite;
    private ArrayList<BufferedImage> frames;

    private boolean falling;

    public Payload(int dx, int dy, int x, int y) {
        super(x, y);
        this.dx = dx;
        this.dy = dy;
        this.falling = true;
        this.reach = false;
        this.weight = 2;
        this.obj_width = 161;
        this.obj_height = 91;
        animationTime = 0;
        animationSpeed = 150;
        animationTimeBefore = 0;
        animationTimePrev = 0;
        currentFrame = 6;
        Sprite = null;
        this.frames = new ArrayList<>();
        this.loadImage();
    }

    public void setAnimationSpeed(int speed) {
        this.animationSpeed = speed;
    }

    @Override
    public void loadImage() {
        URL url = this.getClass().getResource("PL.png");
        try {
            Sprite = ImageIO.read(url);
        } catch (IOException ex) {
            System.out.println("no image");
        }

        //Stay (0-5)// 
        frames.add(Sprite.getSubimage(15, 23, 165, 91));
        frames.add(Sprite.getSubimage(221, 23, 165, 91));
        frames.add(Sprite.getSubimage(417, 23, 165, 91));
        frames.add(Sprite.getSubimage(624, 23, 165, 91));
        frames.add(Sprite.getSubimage(844, 23, 165, 91));
        frames.add(Sprite.getSubimage(1044, 23, 165, 91));

        //Moving (6-9)//
        frames.add(Sprite.getSubimage(15, 142, 165, 91));
        frames.add(Sprite.getSubimage(221, 142, 165, 91));
        frames.add(Sprite.getSubimage(416, 142, 163, 91));
        frames.add(Sprite.getSubimage(624, 142, 165, 91));

    }

    public void animation() {
        animationTime = System.currentTimeMillis();

        if (animationTime - animationTimePrev >= animationSpeed) {
            currentFrame++;
            if (currentFrame > 9) {
                currentFrame = 6;
            }
            Sprite = flipHorizontal(frames.get(currentFrame));
            animationTimePrev = animationTime;
        }
    }

    public void move() {

        animation();
        x += dx;
        y += dy;
        if (falling) {
            dy += weight;
            if (dy >= 10) {
                dy = 10;
            }
        }
        for (Tile tile : map.tile_list) {
            if (new Rectangle(x, y + 70, obj_width, obj_height - 70).intersects(tile.getBound()) && tile.getTouchAble() == true) {
                y = tile.getY() - obj_height + 10;
                dy = 0;
                falling = false;
            } else {
                falling = true;
            }
        }

        for (InGameObject p : Team_A) {
            if (((InGameObjects.Characters) p).isDead() == true) {
                continue;
            }
            if (this.getHitbox().intersects(p.getHitbox())) {
                dx = 1;
                break;
            }
            dx = 0;
        }

        for (InGameObject p : Team_B) {
            if (((InGameObjects.Characters) p).isDead() == true) {
                continue;
            }
            if (this.getHitbox().intersects(p.getHitbox())) {
                dx = 0;
                break;
            }
        }

        // Checking , which team's payload reach the goal // 
        if (this.getHitbox().contains(goal.getHitbox())) {
            this.reach = true;
        }

    }

    public final BufferedImage flipHorizontal(BufferedImage b) {
        BufferedImage fliped;
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-b.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        fliped = op.filter(b, null);
        return fliped;
    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(x - 50, y - 10, obj_width + 100, obj_height + 100);
    }

    public void draw(Graphics g) {
        g.drawImage(Sprite, x, y, null);

    }

    public boolean isReach() {
        if(reach == true){
            dx = 0;
        }
        return reach;
    }

    public void setReach(boolean reach) {
        this.reach = reach;
    }

}
