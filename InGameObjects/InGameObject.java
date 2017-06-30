/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InGameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author iceyo
 */
public abstract class InGameObject {

    protected int x;
    protected int y;
    protected int dx;
    protected int dy;

    protected int weight;
    protected int obj_width;
    protected int obj_height;

    public InGameObject(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public abstract void draw(Graphics g);

    public abstract void move();

    public abstract void loadImage();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getHitbox() {
        return new Rectangle(x + 10, y, obj_width, obj_height);
    }

}
