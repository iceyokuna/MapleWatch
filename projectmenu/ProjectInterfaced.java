package projectmenu;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import projectmenu_credit.Projectmenu_Credit;



import projectmenu_job.ProjectMenu_Job;
import projectmenu_tutor.ProjectMenu_Tutor;

/**
 *
 * @author Ham
 */
public class ProjectInterfaced extends javax.swing.JFrame {

    /**
     * Creates new form ProjectInterfaced
     */
    public ProjectInterfaced() {

        initComponents();
        customCursor();
        String filename = "src\\projectmenu\\Overwatch - Theme Login Music.wav";
        playSound(filename);
  
    }
    static public void playSound(String filename){
        File sound = new File(filename);
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
        }catch(Exception e){
            System.out.println("no file for sound");
        }
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    static int count = 0;
    static int count_but_2 = 0;
    static int count_but_3 = 0;
    static int count_but_4 = 0;
    
    
    public void customCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image_c = toolkit.getImage("maple_cusor.png");
        Cursor c = toolkit.createCustomCursor(image_c, new Point(0,
                0), "img");
        this.setCursor(c);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/play_button.png"))); // NOI18N
        jButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButton1StateChanged(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 450, 250, 80));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/exit_button.png"))); // NOI18N
        jButton2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButton2StateChanged(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 590, 250, 80));

        jLabel1.setFont(new java.awt.Font("Segoe Marker", 3, 60)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MapleWatch");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 350, 110));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/mapleleaf--1- (2).png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 120, 120));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/tutor_button.png"))); // NOI18N
        jButton3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButton3StateChanged(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 600, 250, 80));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/about_button.png"))); // NOI18N
        jButton4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButton4StateChanged(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 590, 250, 80));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/wooden.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, -40, -1, 460));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/819429-maplestory-wallpaper.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //exit button
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButton1StateChanged
        // TODO add your handling code here:
        // play
        if (count % 1 == 0) {
            jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/play_button.png")));
        }
        if (count % 2 == 0) {
            jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/play_passed.png")));
        }
        count++;
    }//GEN-LAST:event_jButton1StateChanged
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFrame menu = new ProjectMenu_Job();
        menu.setVisible(true);
        this.setVisible(false);
        //this.add(menu,BorderLayout.CENTER); 
        //JPanel menu  = new menuJob_Panel();
        //this.add(new menuJob_Panel());
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //credit push
        JFrame credit = new Projectmenu_Credit();
        credit.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButton3StateChanged
        // TODO add your handling code here:
        // tutorial
        if (count_but_3 % 1 == 0) {
            jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/tutor_button.png")));
        }
        if (count_but_3 % 2 == 0) {
            jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/tutor_passed.png")));
        }
        count_but_3++;
    }//GEN-LAST:event_jButton3StateChanged

    private void jButton4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButton4StateChanged
        // TODO add your handling code here:
        //credit
        if (count_but_4 % 1 == 0) {
            jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/about_button.png")));
        }
        if (count_but_4 % 2 == 0) {
            jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/about_passed.png")));
        }
        count_but_4++;

    }//GEN-LAST:event_jButton4StateChanged

    private void jButton2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButton2StateChanged
        // TODO add your handling code here:
        //exit
        if (count_but_2 % 1 == 0) {
            jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/exit_button.png")));
        }
        if (count_but_2 % 2 == 0) {
            jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmenu/exit_passed.png")));
        }
        count_but_2++;
    }//GEN-LAST:event_jButton2StateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JFrame tutor = new ProjectMenu_Tutor();
        tutor.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProjectInterfaced.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectInterfaced.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectInterfaced.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectInterfaced.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjectInterfaced().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
