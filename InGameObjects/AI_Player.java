/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InGameObjects;

import Job.Job;
import Map.MapA;
import Map.Tile;
import java.awt.Rectangle;
import static maplewatch.Game.Team_A;
import static maplewatch.Game.Team_B;
import static maplewatch.Game.payload;

/**
 *
 * @author iceyo
 */
public class AI_Player extends Characters {

    private int distance;
    private int prepare_distance;
    private AI_behavior state;
    private Boolean workdone;
    private Boolean ready;

    public AI_Player(int x, int y, Job job, Team team , String name) {
        super(x, y, job, team , name);
        distance = 0;
        prepare_distance = 0;
        workdone = true;
        ready = true;
    }
    
    public AI_Player(int x, int y, Job job, Team team) {
        super(x, y, job, team , "BOT");
        distance = 0;
        prepare_distance = 0;
        workdone = true;
        ready = true;
    }

    public void AI_hold() {
        dx = 0;
    }

    public void AI_goleft() {
        dx = -2;
        dir = Direction.LEFT;
    }

    public void AI_goright() {
        dx = 2;
        dir = Direction.RIGHT;
    }

    public void AI_jump() {
        if (jumping == false) {
            dy = -17;
            jumping = true;
        }
    }

    public void AI_down() {
        if (jumping == false && this.y < 450) {
            y += 15;
            jumping = true;
        }
    }

    public void AI_melee() {
        if (job.getAttackable() == true) {
            job.meleeattack();
        }
    }

    public void AI_range() {
        if (job.getAttackable() == true) {
            job.rangeattack();
        }
    }

    public void AI_Ultimate() {
        if (job.getAttackable() == true && this.Sp == 50) {
            job.ultimateattack();
        }
    }

    public void AI_skill() {
        System.out.println("not support yet");
    }

    //AI logical movement
    public void logical_move() {
        if (isDead == true) {
            state = AI_behavior.ESCAPE;
            return;
        } else if (this.team == Team.TEAM_A) {
            state = AI_behavior.PUSH;
        } else {
            state = AI_behavior.PROTECT;
        }
        if (workdone == false) {
            return;
        }

        if (new Rectangle(x + 10, y - 500, 50, 500).intersects(payload.getHitbox()) && !this.getHitbox().intersects(payload.getHitbox())) {
            this.AI_jump();
        }
        if (new Rectangle(x + 10, y + 75, 50, 500).intersects(payload.getHitbox()) && !this.getHitbox().intersects(payload.getHitbox())) {
            this.AI_down();
        }
        //team = A
        if (this.team == Team.TEAM_A) {
            //Intersect within team
            for (InGameObject p : Team_A) {
                if (this.getHitbox().intersects(p.getHitbox()) && !this.equals(p)) {
                    state = AI_behavior.FREE;
                    if (((Characters) p).getDir() == Direction.LEFT) {
                        this.dir = Direction.RIGHT;
                    } else {
                        this.dir = Direction.LEFT;
                    }
                }
            }

            for (InGameObject p : Team_B) {
                if (new Rectangle(x + 10, y - 125, 50, 125).intersects(p.getHitbox()) || new Rectangle(x - 240, y - 100, 575, 125).intersects(p.getHitbox())) {
                    this.AI_jump();
                }
                if (new Rectangle(x + 10, y + 75, 50, 150).intersects(p.getHitbox())) {
                    this.AI_down();
                }
            }
        } //team b = B
        else {
            //Intersect within team
            for (InGameObject p : Team_B) {
                if (this.getHitbox().intersects(p.getHitbox()) && !this.equals(p)) {
                    state = AI_behavior.FREE;
                    if (((Characters) p).getDir() == Direction.LEFT) {
                        this.dir = Direction.RIGHT;
                    } else {
                        this.dir = Direction.LEFT;
                    }
                }
            }
            for (InGameObject p : Team_A) {
                if (new Rectangle(x + 10, y - 125, 50, 125).intersects(p.getHitbox()) || new Rectangle(x - 240, y - 100, 575, 125).intersects(p.getHitbox())) {
                    this.AI_jump();
                }
                if (new Rectangle(x + 10, y + 75, 50, 150).intersects(p.getHitbox())) {
                    this.AI_down();
                }
            }
        }
    }

    // AI logic action
    public void logical_action() {
        //Team A should do
        if (state == AI_behavior.ESCAPE || state == AI_behavior.GOHEAL || state == AI_behavior.FREE) {
            return;
        }
        if (this.team == Team.TEAM_A) {
            //check action in team a
            for (InGameObject p : Team_B) {
                if (((InGameObjects.Characters) p).isDead() == true) {
                    continue;
                }
                if (this.getHitbox().intersects(p.getHitbox())) {
                    workdone = false;
                    state = AI_behavior.ATTACK_MELEE;
                    break;
                } else if (new Rectangle(x - 240, y, 250, 75).intersects(p.getHitbox())) {
                    workdone = false;
                    this.dir = Direction.LEFT;
                    state = AI_behavior.ATTACK_RANGE;
                    if (Sp == 50) {
                        AI_Ultimate();
                    }
                    break;
                } else if (new Rectangle(x + 60, y, 250, 75).intersects(p.getHitbox())) {
                    workdone = false;
                    this.dir = Direction.RIGHT;
                    state = AI_behavior.ATTACK_RANGE;
                    if (Sp == 50) {
                        AI_Ultimate();
                    }
                    break;
                } else {
                    workdone = true;
                }
            }
            //Team b should do
        } else {
            //check action in team b
            for (InGameObject p : Team_A) {
                if (((InGameObjects.Characters) p).isDead() == true) {
                    continue;
                }
                if (this.getHitbox().intersects(p.getHitbox())) {
                    workdone = false;
                    state = AI_behavior.ATTACK_MELEE;
                    break;
                } else if (new Rectangle(x - 240, y, 250, 75).intersects(p.getHitbox())) {
                    workdone = false;
                    this.dir = Direction.LEFT;
                    state = AI_behavior.ATTACK_RANGE;
                    if (Sp == 50) {
                        AI_Ultimate();
                    }
                    break;
                } else if (new Rectangle(x + 60, y, 250, 75).intersects(p.getHitbox())) {
                    workdone = false;
                    this.dir = Direction.RIGHT;
                    state = AI_behavior.ATTACK_RANGE;
                    if (Sp == 50) {
                        AI_Ultimate();
                    }
                    break;
                } else {
                    workdone = true;
                }
            }
        }
    }

    public void AI_action() {
        if (null != state) {
            switch (state) {
                case ATTACK_MELEE:
                    this.AI_melee();
                    break;
                case ATTACK_RANGE:
                    this.AI_range();
                    break;
                case ESCAPE:
                    if (this.team == Team.TEAM_A) {
                        this.AI_goleft();
                    } else {
                        this.AI_goright();
                    }
                    break;
                case FREE:
                    if (distance > 50) {
                        distance = 0;
                    }
                    distance += 1;
                    if (this.dir == Direction.LEFT) {
                        this.AI_goleft();
                    } else {
                        this.AI_goright();
                    }
                    break;
                case GOHEAL: ///////////////////////////////////////////////////
                    break;
                case PROTECT:
                    if (distance != 0) {
                        distance += 1;
                        if (distance > 50) {
                            distance = 0;
                        }
                        break;
                    }
                    if (payload.getX() < x && distance == 0) {
                        this.AI_goleft();
                    } else {
                        this.AI_goright();
                    }
                    break;
                case PUSH:
                    if (distance != 0) {
                        distance += 1;
                        if (distance > 50) {
                            distance = 0;
                        }
                        break;
                    }
                    if (payload.getX() > x && distance == 0) {
                        this.AI_goright();
                    } else {
                        this.AI_goleft();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void move() {
        x += dx;
        y += dy;

        prepare_distance += 1;

        if (prepare_distance > 50) {
            prepare_distance = 0;
            ready = true;
        }

        if (falling || jumping) {
            dy += weight;
            if (dy >= 10) {
                dy = 10;
            }
        }

        this.logical_move();
        this.logical_action();
        this.AI_action();
        checkActionCharacter();

        for (Tile tile : MapA.tile_list) {
            if (ready == true && new Rectangle(x - 30, y - 75, 140, 125).intersects(tile.getBound())) {
                if ((int) (Math.random() * 2 + 1) == 1) {
                    AI_jump();
                }
                ready = false;
            }
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
    }
}
