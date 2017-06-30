package projectmenu_credit;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Projectmenu_Credit extends JFrame {
 int x = 0, y = 100,count=0;
    JLabel label1;
    JLabel label2;
    public Projectmenu_Credit() throws HeadlessException {
        //this.setSize(1280,720);
        
        this.setSize(new Dimension(1310,775));
        this.setLocationRelativeTo(null);
        //this.setPreferredSize(new Dimension(1280,720));
        //this.setLocation(6,0);
        //this.setLayout(null);
        
        label1 =  new JLabel("Click here to go back",SwingConstants.RIGHT);
        label1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/mapleleaf--1- (2).png")));
        //label2 = new JLabel("");
        //label2.setLocation(new Point(400,400));
        label1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        this.add(label1);
        //this.add(label2);
        this.getContentPane().setBackground(Color.BLACK);
        
        
    }
private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        this.dispose();
    }     
 
 @Override
 public void paint(Graphics g) {
 super.paint(g);
 Graphics2D g2 = (Graphics2D) g;
 Font font = new Font("Segoe Marker", Font.BOLD+Font.PLAIN,60);
 g2.setFont(font);
 g2.setColor(Color.WHITE);
 
 String bra = "Jethnithi Techasatian";
 if (count==0)
     bra = " Project Leader: Pakpoom Kuna";
 if(count==1)
     bra = " Graphic Designer: Jethnithi Techasatian";
 if(count==2)
     bra = " Project Manager: Phat Thaveepholcharoen ";
 if(count==3)
     bra = " Project: Assistance: Sivut Makeeya";
 if(count==4)
     bra = "And The most importance person";
 if(count==5)
     bra = "Ajan Veera Boonjing :)";
 if(count==6)
     this.dispose();
    g2.drawString(bra,x,y);
 try {
  Thread.sleep(25);
} catch(Exception ex) {
}
 y+=10;
 if(y>this.getHeight()) {
   y=0;
   count++;
}
 repaint();
}
public static void main(String[] args) {
 JFrame jf = new Projectmenu_Credit();
 jf.setLocationRelativeTo(null);
 jf.setVisible(true);
}
}