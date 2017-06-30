/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Job;

import AttackTpye.MagicAttack;
import AttackTpye.MeleeAtack;
import AttackTpye.UltimateAttack;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author iceyo
 */
public class Magician extends Job {
    
    public Magician() {
       atk_melee = new MeleeAtack();
       atk_range = new MagicAttack();
       atk_ultimate = new UltimateAttack();
    }

    @Override
    public final void loadImage() {
        URL url = this.getClass().getResource("player2.png");
        try {
            Sprite = ImageIO.read(url);
        } catch (IOException ex) {
            System.out.println("no image");
        }
        
        /// Shooting Arrow (0-2)///
        frames.add(Sprite.getSubimage(0, 10, 82, 89));
        frames.add(Sprite.getSubimage(87, 10, 82, 89));
        frames.add(Sprite.getSubimage(173, 10, 98, 89));
        //////////////////////////////////////////////

        /// Melee Attack (3-4)//
        frames.add(Sprite.getSubimage(0, 107, 91, 81));
        frames.add(Sprite.getSubimage(96, 107, 104, 81));
        //////////////////////////////////////////////

        /// Standing (5-9) ///
        frames.add(Sprite.getSubimage(7, 198, 83, 91));
        frames.add(Sprite.getSubimage(94, 198, 83, 91));
        frames.add(Sprite.getSubimage(181, 198, 83, 91));
        frames.add(Sprite.getSubimage(267, 198, 83, 91));
        frames.add(Sprite.getSubimage(356, 198, 83, 91));
        
        
        /// Walking (10-13) ///
        frames.add(Sprite.getSubimage(0, 296, 83, 81));
        frames.add(Sprite.getSubimage(87, 296, 83, 76));
        frames.add(Sprite.getSubimage(172, 296, 83, 81));
        frames.add(Sprite.getSubimage(259, 296, 83, 84));
        

        /// Jumping (14-16) ///
        frames.add(Sprite.getSubimage(0, 384, 83, 88));
        frames.add(Sprite.getSubimage(92, 384, 83, 88));
        frames.add(Sprite.getSubimage(173, 384, 83, 88));
    }

}
