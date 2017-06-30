/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maplewatch;

import HealthPackType.*;
import InGameObjects.Characters;
import InGameObjects.HealthPack;
import InGameObjects.InGameObject;
import InGameObjects.Wave;

import static maplewatch.Game.DELAY;
import static maplewatch.Game.HealthList;
import static maplewatch.Game.Obj_A;
import static maplewatch.Game.Obj_B;
import static maplewatch.Game.Team_A;
import static maplewatch.Game.Team_B;

/**
 *
 * @author iceyo
 */
public class ObjectThread implements Runnable {

    private Thread t;

    public ObjectThread() {
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

            try {
                for (int i = 0; i < Obj_A.size(); i++) {
                    Obj_A.get(i).move();
                    for (InGameObject p : Team_B) {
                        if (((InGameObjects.Characters) p).isDead() == true) {
                            continue;
                        }
                        if (p.getHitbox().intersects(Obj_A.get(i).getHitbox())) {
                            ((Wave) Obj_A.get(i)).setActive(false);
                            ((Wave) Obj_A.get(i)).ownerCharge();
                            ((InGameObjects.Characters) p).getPain(((Wave) Obj_A.get(i)).getDagame(), ((Wave) Obj_A.get(i)).getOwnerName());
                        }
                    }
                    if (((Wave) Obj_A.get(i)).getActive() == false) {
                        Obj_A.remove(i);
                    }
                }

                for (int i = 0; i < Obj_B.size(); i++) {
                    Obj_B.get(i).move();
                    for (InGameObject p : Team_A) {
                        if (((InGameObjects.Characters) p).isDead() == true) {
                            continue;
                        }
                        if (p.getHitbox().intersects(Obj_B.get(i).getHitbox())) {
                            ((Wave) Obj_B.get(i)).setActive(false);
                            ((Wave) Obj_B.get(i)).ownerCharge();
                            ((InGameObjects.Characters) p).getPain(((Wave) Obj_B.get(i)).getDagame(), ((Wave) Obj_B.get(i)).getOwnerName());
                        }
                    }
                    if (((Wave) Obj_B.get(i)).getActive() == false) {
                        Obj_B.remove(i);
                    }
                }

                for (int i = 0; i < HealthList.size(); i++) {
                    HealthList.get(i).move();
                    for (InGameObject p : Team_A) {
                        if (p.getHitbox().intersects(HealthList.get(i).getHitbox()) && ((InGameObjects.Characters) p).getHp() < 50) {
                            int randPotion = (int) (Math.random() * 2 + 1);
                            ((Characters) p).setHp(((Characters) p).getHp() + ((HealthPack) (HealthList.get(i))).getHeal());
                            if (randPotion == 1) {
                                HealthList.add(new HealthPack((int) (Math.random() * 134 + 1) * 30, -200, new HealthPack_great()));
                            }
                            if (randPotion == 2) {
                                HealthList.add(new HealthPack((int) (Math.random() * 134 + 1) * 30, -200, new HealthPack_small()));
                            }
                        }
                        if(((HealthPack) (HealthList.get(i))).isActive() == false){
                            HealthList.remove(i);
                        }
                    }
                }

                for (int i = 0; i < HealthList.size(); i++) {
                    HealthList.get(i).move();
                    for (InGameObject p : Team_B) {
                        if (p.getHitbox().intersects(HealthList.get(i).getHitbox()) && ((InGameObjects.Characters) p).getHp() < 50) {
                            int randPotion = (int) (Math.random() * 2 + 1);
                            ((Characters) p).setHp(((Characters) p).getHp() + ((HealthPack) (HealthList.get(i))).getHeal());
                            if (randPotion == 1) {
                                HealthList.add(new HealthPack((int) (Math.random() * 134 + 1) * 30, -200, new HealthPack_great()));
                            }
                            if (randPotion == 2) {
                                HealthList.add(new HealthPack((int) (Math.random() * 134 + 1) * 30, -200, new HealthPack_small()));
                            }
                        }
                        if(((HealthPack) (HealthList.get(i))).isActive() == false){
                            HealthList.remove(i);
                        }
                    }
                }

            } catch (NullPointerException ex) {
                System.out.println("Thread Object Error");
            }
            catch(java.lang.IndexOutOfBoundsException ex){
                System.out.println("Out of Index modic handle");
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
