/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author elshamey
 */
public abstract class Map extends javax.swing.JFrame{
    public void updateTerritories(){}
    public void change(){}
    public JLabel currentStateLbl;
    public JTextField armyCountTF;
    public JButton skipAttackBtn;
    public int additional_armies;
}
