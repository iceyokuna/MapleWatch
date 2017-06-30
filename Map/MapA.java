package Map;

import maplewatch.MapleWatch;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class MapA extends Map {

    //constuctor that invoke load image method
    public MapA() {
        loadImage();
        loadTile();
    }

    //load image
    public void loadImage() {
        ImageIcon ii = new ImageIcon("src\\Map\\Map1.png");
        backgroundImage = ii.getImage();
    }

    public void loadTile() {
        BufferedReader br = null;

        try {
            String sCurrentLine;

            br = new BufferedReader(new FileReader("src\\Map\\textMap1.txt"));

            mapWidth = Integer.parseInt(br.readLine());
            mapHeight = Integer.parseInt(br.readLine());
            array2DMap = new int[mapHeight][mapWidth];

            String delimiters = " ";
            for (int row = 0; row < mapHeight; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delimiters);

                for (int col = 0; col < mapWidth; col++) {
                    array2DMap[row][col] = Integer.parseInt(tokens[col]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        for (int row = 0; row < mapHeight; row++) {

            for (int col = 0; col < mapWidth; col++) {
                int rc = array2DMap[row][col];

                ////// Upper part /////
                if (rc == 1) {
                    tile_list.add(new Tile(78 * col, 33 * row, new Dirt(364, 86, 90, 37), true));
                }

                if (rc == 11) {
                    tile_list.add(new Tile(78 * col, 33 * row, new Dirt(113, 144, 90, 40), false));
                }

                if (rc == 4) {
                    tile_list.add(new Tile(78 * col, 33 * row, new Dirt(264, 86, 90, 37), true));
                }

                if (rc == 14) {
                    tile_list.add(new Tile(78 * col, 33 * row, new Dirt(14, 144, 90, 40), false));
                }

                if (rc == 5) {
                    tile_list.add(new Tile(78 * col, 33 * row, new Dirt(465, 86, 90, 37), true));
                }

                if (rc == 15) {
                    tile_list.add(new Tile(78 * col, 33 * row, new Dirt(212, 144, 90, 40), false));
                }

                if (rc == 2) {
                    tile_list.add(new Tile(89 * col, 33 * row, new Brick(111, 161, 90, 40), true));
                }
                if (rc == 12) {
                    tile_list.add(new Tile(89 * col, 33 * row, new Brick(12, 10, 90, 40), false));
                }

                if (rc == 3) {
                    tile_list.add(new Tile(89 * col, 33 * row, new Brick(210, 161, 90, 40), true));
                }
                if (rc == 13) {
                    tile_list.add(new Tile(89 * col, 33 * row, new Brick(111, 10, 90, 40), false));
                }

                if (rc == 6) {
                    tile_list.add(new Tile(89 * col + 15, 33 * row, new Dirt(119, 87, 42, 38), true));
                }
                if (rc == 16) {
                    tile_list.add(new Tile(89 * col + 15, 33 * row, new Dirt(14, 87, 42, 28), false));
                }

                if (rc == 7) {
                    tile_list.add(new Tile(89 * col + 15, 33 * row, new Dirt(192, 87, 42, 38), true));
                }
                if (rc == 17) {
                    tile_list.add(new Tile(89 * col + 15, 33 * row + 2, new Dirt(68, 87, 40, 25), false));
                }

            }

        }

    }

    //draw image method
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, 4000, 720, this);
        for (Tile tile : tile_list) {
            g.drawImage(tile.getImageTile(), tile.getX(), tile.getY(), null);
        }
        //g.drawImage(backgroundImage, 0, 0, 800, 600, 800, 0, 0, 600, this);
    }
}
