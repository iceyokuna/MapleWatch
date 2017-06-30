package maplewatch;

import HealthPackType.*;
import Job.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import InGameObjects.*;
import Map.*;
import Tools.CounterTime;
import UI.*;
import gameresult.GameResult;
import java.io.File;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Game extends JPanel implements Runnable {

    //Set Delay Varible and aminator that will be a timer of application
    public static final int DELAY = 16;

    private Thread animator;

    // create improtant map variable
    public static Map map;

    //Create Camera object
    public static Camera cam;
    // Create Player object

    public static Player player;
    public static Payload payload;
    public static Goal goal;
    public static int score_teamA;
    public static int score_teamB;
    public static CounterTime gameCounter;
    public static int round;
    public static Team currentTeam = Team.TEAM_A;
    //Create Object ArrayList
    public static ArrayList<InGameObject> Team_A;
    public static ArrayList<InGameObject> Team_B;

    public static ArrayList<InGameObject> Obj_A;
    public static ArrayList<InGameObject> Obj_B;

    public static ArrayList<InGameObject> HealthList;
    public static ArrayList<UI> Ui;

    public static File hurtSound = new File("src\\SoundEffect\\Hit_Hurt.wav");
    public static File ultimateSound_arch = new File("src\\SoundEffect\\Explosion12.wav");
    public static File ultimateSound_sword = new File("src\\SoundEffect\\Explosion4.wav");
    public static File ultimateSound_mage = new File("src\\SoundEffect\\Explosion6.wav");
    public static File bowPull = new File("src\\SoundEffect\\bowPull.wav");
    public static File bowRelease = new File("src\\SoundEffect\\bowRelease.wav");
    public static File swordSwing = new File("src\\SoundEffect\\sword.wav");
    public static File spell = new File("src\\SoundEffect\\mage.wav");
    public static File hanzoUlt = new File("src\\SoundEffect\\hanzoUlt.wav");

    //creat Thread to evaluate the Object
    private PlayerThread playerThread;
    private ObjectThread objectThread;
    private PayloadThread payloadThread;

    int check = 0;

    // Inner Class key events
    public class KeyAction extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }

    //Constructor initialize all of variable
    public Game(Job j, int checkMap) {
        Ui = new ArrayList<>();
        Team_A = new ArrayList<>();
        Team_B = new ArrayList<>();

        Obj_A = new ArrayList<>();
        Obj_B = new ArrayList<>();

        HealthList = new ArrayList<>();

        goal = new Goal(4000 - 170, MapleWatch.displayHeight - 150);
        if (checkMap == 1) {
            map = new MapA();
        } else if (checkMap == 2) {
            map = new MapB();
        }

        player = new Player(20, 0, j, Team.TEAM_A);
        payload = new Payload(1, 1, 0, 50);
        cam = new Camera(0, 0);
        round = 0;

        playerThread = new PlayerThread();
        objectThread = new ObjectThread();
        payloadThread = new PayloadThread();
        initGame();
    }

    // swap team when payload reach the checkpoint
    public void swapTeam() {
        int shift = 0;
        for (InGameObject p : Team_A) {
            ((Characters) p).setTeam(Team.TEAM_B);
            ((Characters) p).setHp(50);
            ((Characters) p).respawn(3500 - 200 * shift, -200);
            shift++;
        }

        shift = 0;

        for (InGameObject p : Team_B) {
            ((Characters) p).setTeam(Team.TEAM_A);
            ((Characters) p).setHp(50);
            ((Characters) p).respawn(200 * shift, -200);
            shift++;
        }
        ArrayList<InGameObject> temp;
        temp = Team_A;
        Team_A = Team_B;
        Team_B = temp;

        int position = payload.getX();
        int temp_score = 0;

        if (round == 1) {
            temp_score = score_teamA;
            score_teamA = score_teamB;
            score_teamB = temp_score;
        }

        payload.setX(50);
        payload.setY(0);
        goal.setX(position);
        payload.setReach(false);
    }

    //initalize the Game panel
    private void initGame() {
        addKeyListener(new KeyAction());
        setFocusable(true);
        setDoubleBuffered(true);
        score_teamA = 0;
        score_teamB = 0;
        Team_A.add(player);

        Team_A.add(new AI_Player(50, 0, new Swordman(), Team.TEAM_A, "Bot Iceyozaza"));
        Team_A.add(new AI_Player(50, 0, new Archer(), Team.TEAM_A, "Bot Overtone"));
        Team_A.add(new AI_Player(50, 0, new Swordman(), Team.TEAM_A, "Bot Skittle"));
        Team_A.add(new AI_Player(50, 0, new Magician(), Team.TEAM_A, "Bot Exynoze"));
        Team_A.add(new AI_Player(50, 0, new Magician(), Team.TEAM_A, "Bot Cabboge"));

        Team_B.add(new AI_Player(3000, 0, new Magician(), Team.TEAM_B, "Bot Cabbage"));
        Team_B.add(new AI_Player(3000, 0, new Swordman(), Team.TEAM_B, "Bot Pks"));
        Team_B.add(new AI_Player(3000, 0, new Swordman(), Team.TEAM_B, "Bot Pkm"));
        Team_B.add(new AI_Player(3000, 0, new Swordman(), Team.TEAM_B, "Bot Kennym"));
        Team_B.add(new AI_Player(3000, 0, new Magician(), Team.TEAM_B, "Bot Olofmeister"));
        Team_B.add(new AI_Player(3000, 0, new Archer(), Team.TEAM_B, "Bot GetRight"));

        int randPotion;

        for (int i = 0; i < 10; i++) {
            randPotion = (int) (Math.random() * 2 + 1);
            if (randPotion == 1) {
                HealthList.add(new HealthPack((int) (Math.random() * 134 + 1) * 30, -200, new HealthPack_great()));
            }
            if (randPotion == 2) {
                HealthList.add(new HealthPack((int) (Math.random() * 134 + 1) * 30, -200, new HealthPack_small()));
            }
        }
        Ui.add(new HealtBar());
        Ui.add(new StatusBar());
        Ui.add(new PayloadBar());
        Ui.add(new GameTimerBar());
        Ui.add(new KillBar());
        gameCounter = new CounterTime(180);
    }

    //Use to start the set various initialisation tasks.
    //To invoke run method
    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }

    //draw each object
    @Override
    public synchronized void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //camera tick
        g2d.translate(cam.getX(), cam.getY()); // begin of cam

        //Draw anything here
        map.draw(g);
        goal.draw(g);

        try {
            for (InGameObject p : Team_A) {
                p.draw(g);
            }
            for (InGameObject p : Team_B) {
                p.draw(g);
            }
            for (InGameObject p : Obj_A) {
                p.draw(g);
            }
            for (InGameObject p : Obj_B) {
                p.draw(g);
            }
            for (InGameObject p : HealthList) {
                p.draw(g);
            }

            payload.draw(g);
            //draw UI
            for (UI Ui : Ui) {
                Ui.draw(g);
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("");
        }

        //camera updates
        g.translate(0, 0);
        g2d.translate(-cam.getX(), -cam.getY()); // end of cam
    }

    //Run method as a Game loop
    @Override
    public void run() {
        long timeDiff, sleep;
        long beforeTime = System.currentTimeMillis();
        playerThread.start();
        objectThread.start();
        payloadThread.start();
        while (true) {
            //We want our game run smoothly, at constant speed. Therefore we compute the system time.
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            if (round == 2) {
                round = 3;
                JFrame game = new GameResult();
                game.setVisible(true);
                this.setVisible(false);
            }

            repaint();

            if ((payload.isReach() == true || gameCounter.isTimeout() == true) && round < 2) {
                if (round == 1) {
                    if (gameCounter.isTimeout() == false) {
                        score_teamA = score_teamB;
                    }
                }
                gameCounter = new CounterTime(180);
                round += 1;
                this.swapTeam();
            }

            cam.tick(player.getDx(), player.getX()); // Lock player in the middle.

            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + e.getMessage());
            }
            beforeTime = System.currentTimeMillis();
        }
    }
}
