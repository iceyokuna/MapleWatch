/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InGameObjects;

import AttackTpye.AttackType;
import AttackTpye.MeleeAtack;
import AttackTpye.RangeAttack;
import WaveType.Dragon;
import WaveType.Flame;
import WaveType.Storm;
import WaveType.WaveType;
import java.awt.Graphics;

/**
 *
 * @author iceyo
 */
public class Wave extends InGameObject {

    private WaveType waveType;
    private AttackType attackType;
    private Direction dir;
    private int distance;
    private boolean active;
    private Characters owner;

    public Wave(int x, int y, WaveType waveType, AttackType attackType, Direction dir , Characters owner) {
        super(x, y);
        this.attackType = attackType;
        this.waveType = waveType;
        this.owner = owner;
        this.dir = dir;
        this.distance = 0;
        obj_width = waveType.getWidth();
        obj_height = waveType.getHight();
        active = true;
        this.loadImage();
    }
    
    public String getOwnerName(){
        return owner.getName();
    }
    
    public void ownerCharge(){
        if(owner.getSp() >= 50){
            owner.setSp(50);
        }
        if(attackType instanceof RangeAttack || attackType instanceof MeleeAtack){
            owner.chargeSP();
        }
    }
    
    public boolean getActive(){
        return active;
    }
    
    public int getDagame(){
        if(waveType instanceof Flame){
            return 50;
        }
        return attackType.getDamage();
    }
    
    public void setActive(Boolean active){
        if(waveType instanceof Dragon || waveType instanceof Storm){
            this.active = true;
            return;
        }
        this.active = active;
    }

    @Override
    public void draw(Graphics g) {
        waveType.draw(g, x, y, dir);
    }

    @Override
    public void move() {
        if(distance == waveType.getMaxRange()){
            active = false;
        }
        if (this.dir == Direction.LEFT) {
            dx = -waveType.getSpeed();
        } else {
            dx = waveType.getSpeed();
        }
        
        distance += 1;
        x += dx;
        y += dy;
    }

    @Override
    public void loadImage() {
        waveType.loadImage();
    }
}
