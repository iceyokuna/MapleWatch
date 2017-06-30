/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Job;

import AttackTpye.MeleeAtack;
import AttackTpye.SlashAttack;
import AttackTpye.UltimateAttack;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;



public class Swordman extends Job {

    public Swordman() {
       atk_melee = new MeleeAtack();
       atk_range = new SlashAttack();
       atk_ultimate = new UltimateAttack();
    }

    @Override
    public final void loadImage() {
        URL url = this.getClass().getResource("player3.png");
        try {
            Sprite = ImageIO.read(url);
        } catch (IOException ex) {
            System.out.println("no image");
        }

        /// Shooting Arrow (0-2)///
        frames.add(Sprite.getSubimage(0, 0, 63, 72));
        frames.add(Sprite.getSubimage(64, 0, 84, 72));
        frames.add(Sprite.getSubimage(148, 0, 79, 72));
        //////////////////////////////////////////////

        /// Melee Attack (3-4)//
        frames.add(Sprite.getSubimage(0, 79, 64, 68));
        frames.add(Sprite.getSubimage(64, 79, 76, 68));
        //////////////////////////////////////////////

        /// Standing (5-9) ///
        frames.add(Sprite.getSubimage(0, 154, 61, 69));
        frames.add(Sprite.getSubimage(64, 154, 61, 69));
        frames.add(Sprite.getSubimage(125, 154, 61, 69));
        frames.add(Sprite.getSubimage(188, 154, 61, 69));
        frames.add(Sprite.getSubimage(250, 154, 61, 69));

        /// Walking (10-13) ///
        frames.add(Sprite.getSubimage(3, 227, 61, 69));
        frames.add(Sprite.getSubimage(64, 227, 61, 69));
        frames.add(Sprite.getSubimage(125, 227, 61, 69));
        frames.add(Sprite.getSubimage(188, 227, 61, 69));

        /// Jumping (14-16) ///
        frames.add(Sprite.getSubimage(2, 301, 61, 76));
        frames.add(Sprite.getSubimage(64, 301, 61, 76));
        frames.add(Sprite.getSubimage(124, 301, 61, 76));
    }

}
