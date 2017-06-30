/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HealthPackType;

import java.awt.Graphics;

/**
 *
 * @author Ham
 */
public interface HealthPack_types {
    public void loadimage();
    public void draw(Graphics g,int x,int y);
    public int getHealth();
}
