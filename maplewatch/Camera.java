/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maplewatch;

/**
 *
 * @author Phat
 */
public class Camera {

    private int x, y;

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void tick(int pdx, int px) {
        if (px <= MapleWatch.displayWidth / 2 - 50) {
            x = 0;
            return;
        }
        if (px >= 3310) {
            x = -2720;
            return;
        }
        if (px + x > MapleWatch.displayWidth / 2 - 50) {
            x = 590 - px;
        }

        x = (px-590) * (-1);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
