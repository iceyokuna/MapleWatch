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
public interface TileType {
    
   public void loadImage();
   
   public BufferedImage getImageTile();
   
   public int getWidth();
   
   public int getHeight();
    
}
