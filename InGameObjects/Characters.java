/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InGameObjects;

import AttackTpye.RangeAttack;
import AttackTpye.UltimateAttack;
import Job.*;
import Map.MapA;
import Map.Tile;
import Tools.CounterTime;
import static UI.KillBar.bar;
import WaveType.Arrow;
import WaveType.Dragon;
import WaveType.Flame;
import WaveType.Magic;
import WaveType.Slash;
import WaveType.Storm;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import static maplewatch.Game.Obj_A;
import static maplewatch.Game.Obj_B;
import static maplewatch.Game.Team_A;
import static maplewatch.Game.Team_B;
import static maplewatch.Game.bowRelease;
import static maplewatch.Game.hanzoUlt;
import static maplewatch.Game.hurtSound;
import static maplewatch.Game.spell;
import static maplewatch.Game.swordSwing;
import static maplewatch.Game.ultimateSound_mage;
import static maplewatch.Game.ultimateSound_sword;

/**
 *
 * @author iceyo
 */
public abstract class Characters extends InGameObject {

    protected int Hp;
    protected int Sp;
    protected Job job;
    protected Direction dir;
    protected Team team;
    protected boolean jumping;
    protected boolean falling;
    protected boolean isDead;
    protected int cooldownDead = 5;
    protected CounterTime deadTimer;
    protected String Name;
    protected String lastTouch;

    public Characters(int x, int y, Job job, Team team, String name) {
        super(x, y);
        this.Hp = 50;
        this.Sp = 0;
        this.Name = name;
        this.weight = 1;
        this.jumping = false;
        this.falling = true;
        this.job = job;
        this.team = team;
        this.dir = Direction.RIGHT;
        this.obj_width = 50;
        this.obj_height = 75;
        this.isDead = false;
        this.loadImage();
    }

    static public void playSound_f(File f) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(f));
            clip.start();
        } catch (Exception e) {
            System.out.println("no file for sound");
        }
    }

    public void setTeam(Team newTeam) {
        this.team = newTeam;
    }

    public String getName() {
        return Name;
    }

    public void getPain(int damage, String touchname) {
        if ((int) (Math.random() * 2 + 1) == 1) {
            playSound_f(hurtSound);
        }
        this.lastTouch = touchname;
        Hp -= damage;
        Sp += 1;
        if (Sp >= 50) {
            Sp = 50;
        }
    }

    public void respawn(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setHp(int newHp) {
        if (newHp > 50) {
            this.Hp = 50;
        } else {
            this.Hp = newHp;
        }
    }

    public Team getTeam() {
        return team;
    }

    public void dead() {
        this.isDead = true;
        bar.add(lastTouch + " -KILL- " + Name);
        deadTimer = new CounterTime(5);
    }

    public boolean isDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public int getCurrentDeadTime() {
        return deadTimer.getCurrentSeconds();
    }

    public int getCooldownDead() {
        return (int) cooldownDead;
    }

    public Job getJob() {
        return job;
    }

    public int getHp() {

        return Hp;
    }

    public int getSp() {
        return Sp;
    }

    public void setSp(int Sp) {
        this.Sp = Sp;
    }

    public void chargeSP() {
        Sp += 2;
    }

    @Override
    public final void loadImage() {
        job.loadImage();
    }

    public Direction getDir() {
        return dir;
    }

    @Override
    public void draw(Graphics g) {
        if (isDead == false) {
            this.job.animation(dx, dir);
            this.job.draw(g, x, y);
        } else {
            this.job.drawSoul(g, x, y - 15);
        }
    }

    public void checkActionCharacter() {
        if ("MeleeAttack".equals(job.getCurrentState())) {
            if (this.team == Team.TEAM_A) {
                for (InGameObject p : Team_B) {
                    if (job.getMeleeHitbox(x, y).intersects(p.getHitbox())) {
                        ((Characters) p).getPain(job.getMeleeDamage(), this.Name);
                    }
                    job.finishAttacking();

                }
            } else {
                for (InGameObject p : Team_A) {
                    if (job.getMeleeHitbox(x, y).intersects(p.getHitbox())) {
                        ((Characters) p).getPain(job.getMeleeDamage(), this.Name);
                    }
                    job.finishAttacking();
                }
            }
        }
        if ("RangeAttack".equals(job.getCurrentState())) {
            if (this.team == Team.TEAM_A) {
                Obj_A.add(new Wave(x, y + 25, new Arrow(), new RangeAttack(), this.dir, this));
                job.finishAttacking();
                this.playSound_f(bowRelease);
            } else {
                Obj_B.add(new Wave(x, y + 25, new Arrow(), new RangeAttack(), this.dir, this));
                job.finishAttacking();
            }
        }

        if ("MagicAttack".equals(job.getCurrentState())) {
            if (this.team == Team.TEAM_A) {
                Obj_A.add(new Wave(x, y + 25, new Magic(), new RangeAttack(), this.dir, this));
                job.finishAttacking();
                this.playSound_f(spell);
            } else {
                Obj_B.add(new Wave(x, y + 25, new Magic(), new RangeAttack(), this.dir, this));
                job.finishAttacking();
            }
        }

        if ("SlashAttack".equals(job.getCurrentState())) {
            if (this.team == Team.TEAM_A) {
                Obj_A.add(new Wave(x, y + 25, new Slash(), new RangeAttack(), this.dir, this));
                job.finishAttacking();
                this.playSound_f(swordSwing);
            } else {
                Obj_B.add(new Wave(x, y + 25, new Slash(), new RangeAttack(), this.dir, this));
                job.finishAttacking();
                this.playSound_f(swordSwing);
            }
        }
        if ("UltimateAttack".equals(job.getCurrentState())) {
            if (this.team == Team.TEAM_A) {
                if (this.job instanceof Archer) {
                    Obj_A.add(new Wave(x, y - 10, new Dragon(), new UltimateAttack(), this.dir, this));
                    this.playSound_f(hanzoUlt);
                } else if (this.job instanceof Magician) {
                    Obj_A.add(new Wave(x, y - 5, new Flame(), new UltimateAttack(), this.dir, this));
                    this.playSound_f(ultimateSound_mage);
                } else if (this.job instanceof Swordman) {
                    Obj_A.add(new Wave(x, y - 5, new Storm(), new UltimateAttack(), this.dir, this));
                    this.playSound_f(ultimateSound_sword);
                }
                job.finishAttacking();
            } else {
                if (this.job instanceof Archer) {
                    Obj_A.add(new Wave(x, y - 10, new Dragon(), new UltimateAttack(), this.dir, this));
                    this.playSound_f(hanzoUlt);
                } else if (this.job instanceof Magician) {
                    Obj_A.add(new Wave(x, y - 5, new Flame(), new UltimateAttack(), this.dir, this));
                    this.playSound_f(ultimateSound_mage);
                } else if (this.job instanceof Swordman) {
                    Obj_A.add(new Wave(x, y - 5, new Storm(), new UltimateAttack(), this.dir, this));
                    this.playSound_f(ultimateSound_sword);
                }
                job.finishAttacking();

            }
            Sp = 0;
        }
    }

    public void move() {
        x += dx;
        y += dy;

        if (falling || jumping) {
            dy += weight;
            if (dy >= 10) {
                dy = 10;
            }
        }

        for (Tile tile : MapA.tile_list) {
            if (new Rectangle(x + 15, y + obj_height - 20, obj_width, 10).intersects(tile.getBound()) && tile.getTouchAble() == true) {
                y = tile.getY() - 65;
                dy = 0;
                falling = false;
                jumping = false;
            } else {
                falling = true;
            }
        }
        if (x <= 5) {
            x = 6;
        }
        if (x >= 3945) {
            x = 3944;
        }
        if (isDead == false) {
            this.checkActionCharacter();
        }
    }

}
