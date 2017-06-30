package InGameObjects;

import Job.Job;
import java.awt.event.KeyEvent;

public class Player extends Characters {

    public Player(int x, int y, Job job, Team team, String name) {
        super(x, y, job, team, name);
    }

    public Player(int x, int y, Job job, Team team) {
        super(x, y, job, team, "Player");
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_Z && isDead == false) {
            if (job.getAttackable() == true) {
                job.meleeattack();
            }
        }

        if (key == KeyEvent.VK_X && isDead == false) {
            if (job.getAttackable() == true) {
                job.rangeattack();
            }
        }

        if (key == KeyEvent.VK_C && isDead == false) {
            if (job.getAttackable() == true && this.Sp >= 45) {
                job.ultimateattack();
            }
        }

        if (key == KeyEvent.VK_LEFT && x > 10) {
            dx = -2;
            dir = Direction.LEFT;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
            dir = Direction.RIGHT;
        }

        if (key == KeyEvent.VK_UP && jumping == false) {
            dy = -17;
            jumping = true;
        }
        if (key == KeyEvent.VK_DOWN && jumping == false && this.y < 450) {
            y += 15;
            jumping = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP && jumping == false) {
            jumping = false;
        }
    }
}
