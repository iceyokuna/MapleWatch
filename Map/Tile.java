package Map;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author iceyo
 */
public class Tile {

    private int x;
    private int y;

    boolean touchable;
    
    private TileType tileType;

    public Tile(int x, int y, TileType t , Boolean touchable) {
        this.x = x;
        this.y = y;
        this.touchable  = touchable;
        tileType = t;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean getTouchAble(){
        return touchable;
    }

    public int getWidth() {
        return tileType.getWidth();
    }

    public int getHeight() {
        return tileType.getHeight();
    }

    public Rectangle getBound() {
        return new Rectangle(x, y, tileType.getWidth(), 1);
    }

    public BufferedImage getImageTile() {
        return tileType.getImageTile();
    }

}
