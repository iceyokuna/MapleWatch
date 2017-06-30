/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AttackTpye;

/**
 *
 * @author iceyo
 */
public class MagicAttack implements AttackType{

    @Override
    public int attackCooldown() {
        return 300;
    }

    @Override
    public int getDamage() {
        return 20;
    }
    
}
