/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maplewatch;

import InGameObjects.Characters;

import InGameObjects.InGameObject;
import static UI.KillBar.bar;

import static maplewatch.Game.DELAY;
import static maplewatch.Game.Team_A;
import static maplewatch.Game.Team_B;

/**
 *
 * @author iceyo
 */
public class PlayerThread implements Runnable {

    private Thread t;

    public PlayerThread() {
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

            for (InGameObject p : Team_A) {
                p.move();
                if (((Characters) p).getHp() <= 0) {

                    ((Characters) p).dead();
                    ((Characters) p).setHp(50);

                }
                if (((Characters) p).isDead() == true) {
                    if (((Characters) p).getCurrentDeadTime() == ((Characters) p).getCooldownDead()) {
                        ((Characters) p).respawn(50, 0);
                        if (bar.size() > 0) {
                            bar.remove(0);
                        }
                        ((Characters) p).setIsDead(false);
                    }

                }
            }
            for (InGameObject p : Team_B) {
                p.move();
                if (((Characters) p).getHp() <= 0) {
                    ((Characters) p).dead();
                    ((Characters) p).setHp(50);

                }
                if (((Characters) p).isDead() == true) {
                    if (((Characters) p).getCurrentDeadTime() == ((Characters) p).getCooldownDead()) {
                        ((Characters) p).respawn(3000, 0);
                        if (bar.size() > 0) {
                            bar.remove(0);
                        }
                        ((Characters) p).setIsDead(false);
                    }

                }

            }

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
