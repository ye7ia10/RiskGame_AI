/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import agents.Agent;
import agents.HumanPlayer;
import gameMaps.Map;
import java.awt.Color;
import riskgame.GamePlay;

/**
 *
 * @author mostafanabil198
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form ChooseMap
     */
    public MainMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        USBtn = new javax.swing.JRadioButton();
        egyptBtn = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        jButton1.setText("Play");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, 142, 70));

        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        jButton2.setText("Simulate");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 140, 70));

        buttonGroup1.add(USBtn);
        USBtn.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        USBtn.setText("US MAP");
        getContentPane().add(USBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, -1, -1));

        buttonGroup1.add(egyptBtn);
        egyptBtn.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        egyptBtn.setSelected(true);
        egyptBtn.setText("EGYPT MAP");
        getContentPane().add(egyptBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, -1, -1));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("RISK GAME");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 30)); // NOI18N
        jLabel2.setText("CHOOSE RISK GAME'S MAP");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, -1, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("Human VS Human");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, -1, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Map map = egyptBtn.isSelected() ? new gameMaps.EgyptMap() : new gameMaps.USMap();
        System.out.println(egyptBtn.isSelected());
        GamePlay.getInstance().setGameMap(map);
        System.out.println(map.getClass().getSimpleName());
        ChooseAgents frame = new ChooseAgents(1, egyptBtn.isSelected());
        this.setVisible(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Map map = egyptBtn.isSelected() ? new gameMaps.EgyptMap() : new gameMaps.USMap();
        System.out.println(egyptBtn.isSelected());
        GamePlay.getInstance().setGameMap(map);
        System.out.println(map.getClass().getSimpleName());
        ChooseAgents frame = new ChooseAgents(2, egyptBtn.isSelected());
        this.setVisible(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Map map = egyptBtn.isSelected() ? new gameMaps.EgyptMap() : new gameMaps.USMap();
        System.out.println(egyptBtn.isSelected());
        GamePlay.getInstance().setGameMap(map);
        System.out.println(map.getClass().getSimpleName());
        //ChooseAgents frame = new ChooseAgents(false, egyptBtn.isSelected());
        this.setVisible(false);
        //frame.setVisible(true);
        //frame.setLocationRelativeTo(null);
        Agent[] players = new Agent[2];
        players[0] = new HumanPlayer();
        players[1] = new HumanPlayer();
        
        players[0].setColor(Color.ORANGE);
        players[1].setColor(Color.cyan);
        GamePlay.getInstance().setPlayers(players);
        GamePlay.getInstance().assignTerritories();
        this.setVisible(false);
        if(egyptBtn.isSelected()){
            EgyptMap frame = new EgyptMap(3);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        }else{
            USMap frame = new USMap(3);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        }
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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton USBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton egyptBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
