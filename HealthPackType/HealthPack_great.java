/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HealthPackType;

import Tools.CounterTime;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Ham
 */
public class HealthPack_great implements HealthPack_types {
    protected int health = 30;
    protected BufferedImage image;
    
    
    
    @Override
    public void loadimage() {
        try{
            image = ImageIO.read(this.getClass().getResource("bigPotion.png"));
        } catch (IOException ex){
            System.out.println("no great image");
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
