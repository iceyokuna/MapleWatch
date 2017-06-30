/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Job;

import AttackTpye.AttackType;
import AttackTpye.MagicAttack;
import AttackTpye.RangeAttack;
import AttackTpye.SlashAttack;
import AttackTpye.UltimateAttack;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import InGameObjects.Direction;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author iceyo
 */
public abstract class Job {

    // Image and Animation variable
    protected long animationTime;
    protected long animationTimeBefore;
    protected long animationTimePrev;
    protected long animationSpeed;
    private String attackAnimetion;

    protected int currentFrame;
    protected int currentSoulFrame;
    protected BufferedImage Sprite;
    protected BufferedImage Soul;
    protected ArrayList<BufferedImage> frames;
    protected ArrayList<BufferedImage> soul_frames;

    // Logic about Any action
    protected AttackType atk_melee;
    protected AttackType atk_range;
    protected AttackType atk_ultimate;

    protected boolean attacking;
    protected boolean attackable;

    //Variable -> player point
    public Job() {
        animationTime = 0;
        animationSpeed = 0;
        animationTimeBefore = 0;
        animationTimePrev = 0;
        currentFrame = 5;
        currentSoulFrame = 0;
        attacking = false;
        attackable = true;
        Sprite = null;
        Soul = null;
        attackAnimetion = null;
        this.frames = new ArrayList<>();
        this.soul_frames = new ArrayList<>();
        this.setAnimationSpeed(150);
        this.loadSoulImage();
    }

    public abstract void loadImage();

    public void loadSoulImage() {
        URL url = this.getClass().getResource("Soul.png");
        try {
            Soul = ImageIO.read(url);
        } catch (IOException ex) {
            System.out.println("no image");
        }
        for (int i = 0; i < 12; i++) {
            soul_frames.add(Soul.getSubimage(85 * i, 0, 85, 99));
        }
        Soul = soul_frames.get(0);
    }

    public void setAnimationSpeed(int speed) {
        this.animationSpeed = speed;
    }

    public boolean isAttacking() {
        return this.attacking;
    }

    public void meleeattack() {
        this.attacking = true;
        this.attackable = false;
        attackAnimetion = "meleeattack";
        this.animationSpeed = atk_melee.attackCooldown();
        this.currentFrame = 3;
    }

    public void rangeattack() {
        this.attacking = true;
        this.attackable = false;
        attackAnimetion = "rangeattack";
        this.animationSpeed = atk_range.attackCooldown();
        this.currentFrame = 0;
    }

    public void ultimateattack() {
        this.attacking = true;
        this.attackable = false;
        attackAnimetion = "ultimateattack";
        this.animationSpeed = atk_ultimate.attackCooldown();
        this.currentFrame = 0;
    }

    public boolean getAttackable() {
        return attackable;
    }

    public void finishAttacking() {
        attacking = false;
    }

    public Rectangle getMeleeHitbox(int x, int y) {
        return new Rectangle(x, y, 10, 10);
    }

    public int getMeleeDamage() {
        return atk_melee.getDamage();
    }

    public String getCurrentState() {
        if (currentFrame == 5 && attacking == true && attackAnimetion == "meleeattack") {
            return "MeleeAttack";
        }
        if (currentFrame == 3 && attacking == true && attackAnimetion == "rangeattack") {
            if (atk_range instanceof RangeAttack) {
                return "RangeAttack";
            } else if (atk_range instanceof MagicAttack) {
                return "MagicAttack";
            } else if (atk_range instanceof SlashAttack) {
                return "SlashAttack";
            }
        }

        if (currentFrame == 3 && attacking == true && attackAnimetion == "ultimateattack") {
            if (atk_ultimate instanceof UltimateAttack) {
                return "UltimateAttack";
            }
        }
        return "Stable";
    }

    public void animation(int dx, Direction dir) {
        animationTime = System.currentTimeMillis();
        if (animationTime - animationTimePrev >= animationSpeed) {
            // melee attack
            if ("meleeattack".equals(attackAnimetion)) {
                if (currentFrame > 4) {
                    attacking = false;
                    attackable = true;
                    attackAnimetion = null;
                    this.setAnimationSpeed(100);
                }
                if (dir == Direction.RIGHT) {
                    Sprite = flipHorizontal(frames.get(currentFrame));
                } else {
                    Sprite = frames.get(currentFrame);
                }
                animationTimePrev = animationTime;
                currentFrame++;
                return;
            }

            if ("rangeattack".equals(attackAnimetion) || "ultimateattack".equals(attackAnimetion)) {
                if (currentFrame > 3) {
                    attacking = false;
                    attackable = true;
                    attackAnimetion = null;
                    this.setAnimationSpeed(100);
                }
                if (dir == Direction.RIGHT) {
                    Sprite = flipHorizontal(frames.get(currentFrame));
                } else {
                    Sprite = frames.get(currentFrame);
                }
                animationTimePrev = animationTime;
                currentFrame++;
                return;
            }

            // Normal Move
            currentFrame++;
            if (dx == 0) {
                if (currentFrame > 9) {
                    currentFrame = 5;
                }
                if (dir == Direction.RIGHT) {
                    Sprite = flipHorizontal(frames.get(currentFrame));
                } else {
                    Sprite = frames.get(currentFrame);
                }
            } else {

                if (currentFrame > 13) {
                    currentFrame = 10;
                }
                if (dir == Direction.RIGHT) {
                    Sprite = flipHorizontal(frames.get(currentFrame));
                } else {
                    Sprite = frames.get(currentFrame);
                }
            }
            animationTimePrev = animationTime;
        }
    }

    // Flip an image to be horizontal
    public static final BufferedImage flipHorizontal(BufferedImage b) {
        BufferedImage fliped;
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-b.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        fliped = op.filter(b, null);

        return fliped;
    }

    public void drawSoul(Graphics g, int x, int y) {
        animationTime = System.currentTimeMillis();
        if (animationTime - animationTimePrev >= animationSpeed) {
            if (currentSoulFrame > soul_frames.size() - 1) {
                currentSoulFrame = 0;
            }
            Soul = soul_frames.get(currentSoulFrame);
            currentSoulFrame++;
            animationTimePrev = animationTime;
        }
        g.drawImage(Soul, x, y, null);
    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(Sprite, x, y, null);
    }
}
