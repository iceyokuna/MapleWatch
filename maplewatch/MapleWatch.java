 //@author iceyo project started 14/10/2016
package maplewatch;

import Job.Job;
import gameresult.GameResult;
import javax.swing.*;
import static maplewatch.Game.round;

// Create Application using JFrame
public class MapleWatch extends JFrame {

    // Display Size
    
    public static int displayWidth = 1280;
    public static int displayHeight = 720;
    
    //initalize the MapleWatch Application
    public MapleWatch(Job j,int checkMap) {
        MapleWatchInit(j,checkMap);
    }

    //initialize medthod 
    public void MapleWatchInit(Job j,int checkMap) {
        //Set JFrame
        setSize(displayWidth, displayHeight);
        setTitle("Maple Watch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //Add Panel to JFrame
        add(new Game(j,checkMap));
    }

    //Start the main method the Create the Application
    public static void main(String[] args) {
        //JFrame game = new MapleWatch();
        //game.setVisible(true);
    }
}
