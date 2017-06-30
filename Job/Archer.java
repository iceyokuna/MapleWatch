/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Job;

import AttackTpye.MeleeAtack;
import AttackTpye.RangeAttack;
import AttackTpye.UltimateAttack;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author iceyo
 */
public class Archer extends Job {

    public Archer() {
       atk_melee = new MeleeAtack();
       atk_range = new RangeAttack();
       atk_ultimate = new UltimateAttack();
    }

    @Override
    public final void loadImage() {
        URL url = this.getClass().getResource("player1.png");
        try {
            Sprite = ImageIO.read(url);
        } catch (IOException ex) {
            System.out.println("no image");
        }

        /// Shooting Arrow (0-2)///
        frames.add(Sprite.getSubimage(0, 0, 81, 84));
        frames.add(Sprite.getSubimage(84, 0, 88, 84));
        frames.add(Sprite.getSubimage(175, 0, 83, 84));
        //////////////////////////////////////////////

        /// Melee Attack (3-4)//
        frames.add(Sprite.getSubimage(0, 103, 83, 100));
        frames.add(Sprite.getSubimage(85, 111, 103, 97));
        //////////////////////////////////////////////

        /// Standing (5-9) ///
        frames.add(Sprite.getSubimage(0, 210, 83, 94));
        frames.add(Sprite.getSubimage(86, 210, 83, 94));
        frames.add(Sprite.getSubimage(171, 210, 83, 94));
        frames.add(Sprite.getSubimage(256, 210, 83, 94));
        frames.add(Sprite.getSubimage(341, 210, 83, 94));

        /// Walking (10-13) ///
        frames.add(Sprite.getSubimage(0, 307, 84, 89));
        frames.add(Sprite.getSubimage(86, 307, 84, 89));
        frames.add(Sprite.getSubimage(171, 307, 84, 89));
        frames.add(Sprite.getSubimage(256, 307, 84, 89));

        /// Jumping (14-16) ///
        frames.add(Sprite.getSubimage(0, 402, 81, 83));
        frames.add(Sprite.getSubimage(86, 402, 81, 83));
        frames.add(Sprite.getSubimage(171, 402, 81, 83));
    }

}
