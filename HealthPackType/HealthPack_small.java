package HealthPackType;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Ham
 */
public class HealthPack_small implements HealthPack_types {
    protected int health = 10;
    protected BufferedImage image;
    @Override
    public void loadimage() {
        try{
            image = ImageIO.read(this.getClass().getResource("smallPotion.png"));
        } catch (IOException ex){
            System.out.println("no small image");
        }
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    @Override
    public int getHealth() {
       return health;
    }
    
}
