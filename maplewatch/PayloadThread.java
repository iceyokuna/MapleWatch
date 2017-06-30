/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maplewatch;

import InGameObjects.Team;
import static maplewatch.Game.DELAY;
import static maplewatch.Game.currentTeam;
import static maplewatch.Game.payload;
import static maplewatch.Game.score_teamA;
import static maplewatch.Game.score_teamB;

/**
 *
 * @author iceyo
 */
public class PayloadThread implements Runnable {

    private Thread t;

    public PayloadThread() {
        t = new Thread(this);

    }

    public void start() {
        t.start();
    }

    @Override
    public void run() {
        long timeDiff, sleep;
        long beforeTime = System.currentTimeMillis();

        while (true) {
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            payload.move();

            if (currentTeam == Team.TEAM_A) {
                score_teamA += payload.getDx();
            } else if (currentTeam == Team.TEAM_B) {
                score_teamB += payload.getDx();
            }

            //System.out.println("ScoreTeamA = " + score_teamA + ", " + "ScoreTeamB = " + score_teamB);

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
