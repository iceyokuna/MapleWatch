/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InGameObjects;

import HealthPackType.HealthPack_types;
import Map.Map;
import Map.MapA;
import Map.Tile;
import java.awt.Graphics;
import java.awt.Rectangle;
import static maplewatch.Game.map;

/**
 *
 * @author Ham
 */
public class HealthPack extends InGameObject{
    protected HealthPack_types potion;
    protected boolean falling;
    protected boolean active;

    public HealthPack(int x, int y,HealthPack_types potion) {
        super(x, y);
        this.potion = potion;
        this.weight = 1;
        this.falling = true;
        this.obj_width = 30;
        this.obj_height = 50;
        this.active = true;
        this.loadImage();
    }

    @Override
    public void draw(Graphics g) {
        potion.draw(g, x, y);
    }

    public boolean isActive() {
        return active;
    }
    
    @Override
    public void move() {
        
        y += dy;

        if (falling ) {
            dy += weight;
            if (dy >= 10) {
                dy = 10;
            }
        }

        for (Tile tile : map.tile_list) {
            
            if (this.getHitbox().intersects(tile.getBound()) && tile.getTouchAble() == true) {
                
                y = tile.getY() - 50;
                dy = 0;
                falling = false;
        
            } else {
                falling = true;
            }
        }
    }
    
    @Override
    public void loadImage() {
        potion.loadimage();
    }
    
    public int getHeal()
    {
        this.active = false;
        return this.potion.getHealth();
    }
    
}
