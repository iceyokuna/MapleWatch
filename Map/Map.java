package Map;

import maplewatch.MapleWatch;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public abstract class Map extends JPanel {

    public Image backgroundImage;
    public static ArrayList<Tile> tile_list;
    public int array2DMap[][];
    public int mapWidth;
    public int mapHeight;


    public Map() {
        tile_list = new ArrayList<Tile>();
    }

    //load image
    public abstract void loadImage();

    public abstract void loadTile();

    //draw image method
    public abstract void draw(Graphics g);
}
