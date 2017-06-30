/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author bas25
 */
public class Brick implements TileType {

    private BufferedImage ImageTile;
    private int x;
    private int y;
    private int width;
    private int height;

    public Brick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.loadImage();
    }

    public void loadImage() {
        URL url = this.getClass().getResource("Tile2_edit.png");
        try {
            ImageTile = ImageIO.read(url).getSubimage(x, y, width, height);
        } catch (IOException ex) {
            System.out.println("no image");
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public BufferedImage getImageTile() {
        return this.ImageTile;
    }

}
