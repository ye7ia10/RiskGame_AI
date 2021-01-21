/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import gameMaps.Territory;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.Timer;
import riskgame.GamePlay;

/**
 *
 * @author mostafanabil198
 */
public class USMap extends  Map{

    ArrayList<JButton> territoryBtns = new ArrayList<>();
    int play;
    int additional_armies;
    boolean attacker;
    int attackerTerritory = -1;

    /**
     * Creates new form USMap
     */
    public USMap(int play) {
        initComponents();
        this.play = play;
        attacker = true;
        territoryBtns.add(btn0);
        territoryBtns.add(btn1);
        territoryBtns.add(btn2);
        territoryBtns.add(btn3);
        territoryBtns.add(btn4);
        territoryBtns.add(btn5);
        territoryBtns.add(btn6);
        territoryBtns.add(btn7);
        territoryBtns.add(btn8);
        territoryBtns.add(btn9);
        territoryBtns.add(btn10);
        territoryBtns.add(btn11);
        territoryBtns.add(btn12);
        territoryBtns.add(btn13);
        territoryBtns.add(btn14);
        territoryBtns.add(btn15);
        territoryBtns.add(btn16);
        territoryBtns.add(btn17);
        territoryBtns.add(btn18);
        territoryBtns.add(btn19);
        territoryBtns.add(btn20);
        territoryBtns.add(btn21);
        territoryBtns.add(btn22);
        territoryBtns.add(btn23);
        territoryBtns.add(btn24);
        territoryBtns.add(btn25);
        territoryBtns.add(btn26);
        territoryBtns.add(btn27);
        territoryBtns.add(btn28);
        territoryBtns.add(btn29);
        territoryBtns.add(btn30);
        territoryBtns.add(btn31);
        territoryBtns.add(btn32);
        territoryBtns.add(btn33);
        territoryBtns.add(btn34);
        territoryBtns.add(btn35);
        territoryBtns.add(btn36);
        territoryBtns.add(btn37);
        territoryBtns.add(btn38);
        territoryBtns.add(btn39);
        territoryBtns.add(btn40);
        territoryBtns.add(btn41);
        territoryBtns.add(btn42);
        territoryBtns.add(btn43);
        territoryBtns.add(btn44);
        territoryBtns.add(btn45);
        territoryBtns.add(btn46);
        territoryBtns.add(btn47);
        territoryBtns.add(btn48);
        territoryBtns.add(btn49);
        territoryBtns.add(btn50);
        armyCountTF.setVisible(false);
        armyCountLbl.setVisible(false);
        skipAttackBtn.setVisible(false);

        updateTerritories();
        currentPlayerlbl.setBackground(GamePlay.getInstance().getPlayers()[0].getColor());
        if (play == 2) {
            TIMER.start();
        } else {
            additional_armies = GamePlay.getInstance().currentPlayer().calculateAdditionalArmies();
            currentStateLbl.setText("Place Armies: " + String.valueOf(additional_armies));
            GamePlay.getInstance().nextState();
        }
    }

    public USMap() {
        initComponents();
    }

    public void updateTerritories() {
        ArrayList<Territory> territories = GamePlay.getInstance().getGameMap().territories;
        for (int i = 1; i <= 50; i++) {
            if (territories.get(i).getOwner() != null) {
                territoryBtns.get(i).setBackground(territories.get(i).getOwner().getColor());
            }
            territoryBtns.get(i).setText(String.valueOf(territories.get(i).getArmiesCount()));
        }
        currentPlayerlbl.setBackground(GamePlay.getInstance().currentPlayer().getColor());
    }
public void change()
    {
        this.updateTerritories();
            
            this.currentStateLbl.setText("Player 2's Turn");
            this.armyCountTF.setText("0");
            GamePlay.getInstance().nextState();
            if (GamePlay.getInstance().currentPlayer().win()) {
                //this.setVisible(false);
                //this.setVisible(false);
                Winner frame = new Winner("Human Player Won!");
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
            GamePlay.getInstance().changeTurn();
            this.updateTerritories();
            
            this.skipAttackBtn.setVisible(false);
            this.additional_armies = GamePlay.getInstance().currentPlayer().calculateAdditionalArmies();
            GamePlay.getInstance().nextState();
            this.currentStateLbl.setText("Place Armies: " + String.valueOf(this.additional_armies));
    }
    public final Timer TIMER = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            GamePlay.getInstance().changeTurn();
            ComputerPlaying();
            updateTerritories();
            System.out.println(GamePlay.getInstance().currentPlayer().win());
            if (GamePlay.getInstance().currentPlayer().win()) {
                TIMER.stop();
                exitFrame();
                Winner frame = new Winner(GamePlay.getInstance().currentPlayer().getClass().getSimpleName() + "Player Won!");
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        }
    });

    private void exitFrame() {
        this.setVisible(false);
    }

    private void ComputerPlaying() {
        GamePlay.getInstance().currentPlayer().play();
    }

    private void HumanPlaying(JButton btn) {
        errorLbl.setText("");
        armyCountTF.setVisible(false);
        armyCountLbl.setVisible(false);
        System.out.println(GamePlay.getInstance().currentPlayer().getClass().getSimpleName());
        System.out.println(GamePlay.getInstance().getCurrentPlayerState());
        if ("HumanPlayer".equals(GamePlay.getInstance().currentPlayer().getClass().getSimpleName())) {
            if (GamePlay.getInstance().getCurrentPlayerState() == "place_armies") {
                System.out.println("PLACE ARMIES");
                if (GamePlay.getInstance().currentPlayer().placeArmies(territoryBtns.indexOf(btn))) {
                    System.out.println("TRUE IN" + territoryBtns.indexOf(btn));
                    additional_armies -= 1;
                    updateTerritories();
                    currentStateLbl.setText("Place Armies: " + String.valueOf(additional_armies));
                    if (additional_armies == 0) {
                        GamePlay.getInstance().nextState();
                        currentStateLbl.setText("Select Attacker Territory");
                        skipAttackBtn.setVisible(true);
                    }
                }
            } else if (GamePlay.getInstance().getCurrentPlayerState() == "attack") {
                if (attacker && GamePlay.getInstance().getGameMap().agentHasTerritory(GamePlay.getInstance().currentPlayer(), territoryBtns.indexOf(btn))) {
                    if (GamePlay.getInstance().getGameMap().getTerritory(territoryBtns.indexOf(btn)).getArmiesCount() > 1) {
                        attacker = false;
                        attackerTerritory = territoryBtns.indexOf(btn);
                        btn.setBackground(Color.RED);
                        currentStateLbl.setText("Select Territory To Attack On");
                        armyCountTF.setVisible(true);
                        armyCountLbl.setVisible(true);
                    } else {
                        errorLbl.setText("Can't attack by this territory");
                        updateTerritories();
                    }
                } else if (!attacker) {
                    if (GamePlay.getInstance().getGameMap().isOpponentNeighbours(GamePlay.getInstance().currentPlayer(), attackerTerritory, territoryBtns.indexOf(btn))) {
                        if (Integer.parseInt(armyCountTF.getText()) > 1 && Integer.parseInt(armyCountTF.getText()) < GamePlay.getInstance().getGameMap().getTerritory(attackerTerritory).getArmiesCount()) {
                            if (play == 3) {
                                attack a = new attack(this, attackerTerritory, territoryBtns.indexOf(btn), Integer.parseInt(armyCountTF.getText()));
                                a.setVisible(true);

                            } else {
                                GamePlay.getInstance().currentPlayer().attack(attackerTerritory, territoryBtns.indexOf(btn), Integer.parseInt(armyCountTF.getText()));
                                updateTerritories();
                                currentStateLbl.setText("Player 2's Turn");
                                armyCountTF.setText("0");
                                GamePlay.getInstance().nextState();
                                if (GamePlay.getInstance().currentPlayer().win()) {
                                    this.setVisible(false);
                                    Winner frame = new Winner("Human Player Won!");
                                    frame.setVisible(true);
                                    frame.setLocationRelativeTo(null);
                                }
                                GamePlay.getInstance().changeTurn();
                                ComputerPlaying();
                                updateTerritories();
                                if (GamePlay.getInstance().currentPlayer().win()) {
                                    this.setVisible(false);
                                    Winner frame = new Winner(GamePlay.getInstance().currentPlayer().getClass().getSimpleName() + "Player Won!");
                                    frame.setVisible(true);
                                    frame.setLocationRelativeTo(null);
                                }
                                GamePlay.getInstance().changeTurn();
                                updateTerritories();
                                skipAttackBtn.setVisible(false);
                                additional_armies = GamePlay.getInstance().currentPlayer().calculateAdditionalArmies();
                                GamePlay.getInstance().nextState();
                                currentStateLbl.setText("Place Armies: " + String.valueOf(additional_armies));
                            }
                        } else {
                            errorLbl.setText("!!Can't attack on this territory with this armies count");
                            updateTerritories();
                        }
                    } else {
                        errorLbl.setText("@@Can't attack on this territory with this armies count");
                        updateTerritories();
                    }
                    attacker = true;
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        btn15 = new javax.swing.JButton();
        btn16 = new javax.swing.JButton();
        btn17 = new javax.swing.JButton();
        btn18 = new javax.swing.JButton();
        btn19 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        btn24 = new javax.swing.JButton();
        btn25 = new javax.swing.JButton();
        btn26 = new javax.swing.JButton();
        btn27 = new javax.swing.JButton();
        btn28 = new javax.swing.JButton();
        btn29 = new javax.swing.JButton();
        btn30 = new javax.swing.JButton();
        btn31 = new javax.swing.JButton();
        btn32 = new javax.swing.JButton();
        btn33 = new javax.swing.JButton();
        btn34 = new javax.swing.JButton();
        btn35 = new javax.swing.JButton();
        btn36 = new javax.swing.JButton();
        btn37 = new javax.swing.JButton();
        btn38 = new javax.swing.JButton();
        btn39 = new javax.swing.JButton();
        btn40 = new javax.swing.JButton();
        btn41 = new javax.swing.JButton();
        btn42 = new javax.swing.JButton();
        btn43 = new javax.swing.JButton();
        btn44 = new javax.swing.JButton();
        btn45 = new javax.swing.JButton();
        btn46 = new javax.swing.JButton();
        btn47 = new javax.swing.JButton();
        btn48 = new javax.swing.JButton();
        btn49 = new javax.swing.JButton();
        btn50 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn0 = new javax.swing.JButton();
        armyCountLbl = new javax.swing.JLabel();
        armyCountTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        currentStateLbl = new javax.swing.JLabel();
        errorLbl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        currentPlayerlbl = new javax.swing.JLabel();
        skipAttackBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1500, 1200));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn1.setBackground(java.awt.Color.black);
        btn1.setText("1");
        btn1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn1.setBorderPainted(false);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 40, 30));

        btn2.setBackground(java.awt.Color.black);
        btn2.setText("00");
        btn2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn2.setBorderPainted(false);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        jPanel1.add(btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 40, 30));

        btn3.setBackground(java.awt.Color.black);
        btn3.setText("00");
        btn3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn3.setBorderPainted(false);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        jPanel1.add(btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 480, 40, 30));

        btn4.setBackground(java.awt.Color.black);
        btn4.setText("1");
        btn4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn4.setBorderPainted(false);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        jPanel1.add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 440, 40, 30));

        btn5.setBackground(java.awt.Color.black);
        btn5.setText("1");
        btn5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn5.setBorderPainted(false);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        jPanel1.add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 40, 30));

        btn6.setBackground(java.awt.Color.black);
        btn6.setText("1");
        btn6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn6.setBorderPainted(false);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        jPanel1.add(btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 40, 30));

        btn7.setBackground(java.awt.Color.black);
        btn7.setText("00");
        btn7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn7.setBorderPainted(false);
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        jPanel1.add(btn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, 50, 40));

        btn8.setBackground(java.awt.Color.black);
        btn8.setText("1");
        btn8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn8.setBorderPainted(false);
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        jPanel1.add(btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 460, 40, 40));

        btn9.setBackground(java.awt.Color.black);
        btn9.setText("1");
        btn9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn9.setBorderPainted(false);
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        jPanel1.add(btn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 570, 50, 50));

        btn10.setBackground(java.awt.Color.black);
        btn10.setText("1");
        btn10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn10.setBorderPainted(false);
        btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10ActionPerformed(evt);
            }
        });
        jPanel1.add(btn10, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 470, 60, 40));

        btn11.setBackground(java.awt.Color.black);
        btn11.setText("1");
        btn11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn11.setBorderPainted(false);
        btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn11ActionPerformed(evt);
            }
        });
        jPanel1.add(btn11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 590, 50, 40));

        btn12.setBackground(java.awt.Color.black);
        btn12.setText("1");
        btn12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn12.setBorderPainted(false);
        btn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn12ActionPerformed(evt);
            }
        });
        jPanel1.add(btn12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 650, 50, 50));

        btn13.setBackground(java.awt.Color.black);
        btn13.setText("1");
        btn13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn13.setBorderPainted(false);
        btn13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn13ActionPerformed(evt);
            }
        });
        jPanel1.add(btn13, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 570, 50, 50));

        btn14.setBackground(java.awt.Color.black);
        btn14.setText("1");
        btn14.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn14.setBorderPainted(false);
        btn14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn14ActionPerformed(evt);
            }
        });
        jPanel1.add(btn14, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 510, 60, 30));

        btn15.setBackground(java.awt.Color.black);
        btn15.setText("1");
        btn15.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn15.setBorderPainted(false);
        btn15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn15ActionPerformed(evt);
            }
        });
        jPanel1.add(btn15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 440, 60, 40));

        btn16.setBackground(java.awt.Color.black);
        btn16.setText("1");
        btn16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn16.setBorderPainted(false);
        btn16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn16ActionPerformed(evt);
            }
        });
        jPanel1.add(btn16, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 350, 50, 40));

        btn17.setBackground(java.awt.Color.black);
        btn17.setText("1");
        btn17.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn17.setBorderPainted(false);
        btn17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn17ActionPerformed(evt);
            }
        });
        jPanel1.add(btn17, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 280, 50, 50));

        btn18.setBackground(java.awt.Color.black);
        btn18.setText("1");
        btn18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn18.setBorderPainted(false);
        btn18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn18ActionPerformed(evt);
            }
        });
        jPanel1.add(btn18, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 310, 40, 40));

        btn19.setBackground(java.awt.Color.black);
        btn19.setText("1");
        btn19.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn19.setBorderPainted(false);
        btn19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn19ActionPerformed(evt);
            }
        });
        jPanel1.add(btn19, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 420, 50, 40));

        btn20.setBackground(java.awt.Color.black);
        btn20.setText("1");
        btn20.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn20.setBorderPainted(false);
        btn20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn20ActionPerformed(evt);
            }
        });
        jPanel1.add(btn20, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 510, 50, 40));

        btn21.setBackground(java.awt.Color.black);
        btn21.setText("1");
        btn21.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn21.setBorderPainted(false);
        btn21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn21ActionPerformed(evt);
            }
        });
        jPanel1.add(btn21, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 590, 40, 40));

        btn22.setBackground(java.awt.Color.black);
        btn22.setText("1");
        btn22.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn22.setBorderPainted(false);
        btn22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn22ActionPerformed(evt);
            }
        });
        jPanel1.add(btn22, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 690, 50, 30));

        btn23.setBackground(java.awt.Color.black);
        btn23.setText("1");
        btn23.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn23.setBorderPainted(false);
        btn23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn23ActionPerformed(evt);
            }
        });
        jPanel1.add(btn23, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 640, 40, 40));

        btn24.setBackground(java.awt.Color.black);
        btn24.setText("1");
        btn24.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn24.setBorderPainted(false);
        btn24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn24ActionPerformed(evt);
            }
        });
        jPanel1.add(btn24, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 630, 50, 30));

        btn25.setBackground(java.awt.Color.black);
        btn25.setText("1");
        btn25.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn25.setBorderPainted(false);
        btn25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn25ActionPerformed(evt);
            }
        });
        jPanel1.add(btn25, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 730, 50, 40));

        btn26.setBackground(java.awt.Color.black);
        btn26.setText("1");
        btn26.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn26.setBorderPainted(false);
        btn26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn26ActionPerformed(evt);
            }
        });
        jPanel1.add(btn26, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 630, 50, 40));

        btn27.setBackground(java.awt.Color.black);
        btn27.setText("1");
        btn27.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn27.setBorderPainted(false);
        btn27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn27ActionPerformed(evt);
            }
        });
        jPanel1.add(btn27, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 590, 40, 40));

        btn28.setBackground(java.awt.Color.black);
        btn28.setText("1");
        btn28.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn28.setBorderPainted(false);
        btn28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn28ActionPerformed(evt);
            }
        });
        jPanel1.add(btn28, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 560, 40, 30));

        btn29.setBackground(java.awt.Color.black);
        btn29.setText("1");
        btn29.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn29.setBorderPainted(false);
        btn29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn29ActionPerformed(evt);
            }
        });
        jPanel1.add(btn29, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 500, 40, 30));

        btn30.setBackground(java.awt.Color.black);
        btn30.setText("1");
        btn30.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn30.setBorderPainted(false);
        btn30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn30ActionPerformed(evt);
            }
        });
        jPanel1.add(btn30, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 490, 40, 30));

        btn31.setBackground(java.awt.Color.black);
        btn31.setText("1");
        btn31.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn31.setBorderPainted(false);
        btn31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn31ActionPerformed(evt);
            }
        });
        jPanel1.add(btn31, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 560, 40, 30));

        btn32.setBackground(java.awt.Color.black);
        btn32.setText("1");
        btn32.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn32.setBorderPainted(false);
        btn32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn32ActionPerformed(evt);
            }
        });
        jPanel1.add(btn32, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 510, 40, 30));

        btn33.setBackground(java.awt.Color.black);
        btn33.setText("1");
        btn33.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn33.setBorderPainted(false);
        btn33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn33ActionPerformed(evt);
            }
        });
        jPanel1.add(btn33, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 460, 50, 30));

        btn34.setBackground(java.awt.Color.black);
        btn34.setText("1");
        btn34.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn34.setBorderPainted(false);
        btn34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn34ActionPerformed(evt);
            }
        });
        jPanel1.add(btn34, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 350, 50, 40));

        btn35.setBackground(java.awt.Color.black);
        btn35.setText("1");
        btn35.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn35.setBorderPainted(false);
        btn35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn35ActionPerformed(evt);
            }
        });
        jPanel1.add(btn35, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 460, 40, 30));

        btn36.setBackground(java.awt.Color.black);
        btn36.setText("1");
        btn36.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn36.setBorderPainted(false);
        btn36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn36ActionPerformed(evt);
            }
        });
        jPanel1.add(btn36, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 390, 40, 30));

        btn37.setBackground(java.awt.Color.black);
        btn37.setText("1");
        btn37.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn37.setBorderPainted(false);
        btn37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn37ActionPerformed(evt);
            }
        });
        jPanel1.add(btn37, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 460, 40, 30));

        btn38.setBackground(java.awt.Color.black);
        btn38.setText("00");
        btn38.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn38.setBorderPainted(false);
        btn38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn38ActionPerformed(evt);
            }
        });
        jPanel1.add(btn38, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 420, 40, 30));

        btn39.setBackground(java.awt.Color.black);
        btn39.setText("1");
        btn39.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn39.setBorderPainted(false);
        btn39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn39ActionPerformed(evt);
            }
        });
        jPanel1.add(btn39, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 360, 40, 30));

        btn40.setBackground(java.awt.Color.black);
        btn40.setText("1");
        btn40.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn40.setBorderPainted(false);
        btn40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn40ActionPerformed(evt);
            }
        });
        jPanel1.add(btn40, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 280, 40, 30));

        btn41.setBackground(java.awt.Color.black);
        btn41.setText("00");
        btn41.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn41.setBorderPainted(false);
        btn41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn41ActionPerformed(evt);
            }
        });
        jPanel1.add(btn41, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 260, 40, 30));

        btn42.setBackground(java.awt.Color.black);
        btn42.setText("00");
        btn42.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn42.setBorderPainted(false);
        btn42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn42ActionPerformed(evt);
            }
        });
        jPanel1.add(btn42, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 280, 40, 30));

        btn43.setBackground(java.awt.Color.black);
        btn43.setText("1");
        btn43.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn43.setBorderPainted(false);
        btn43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn43ActionPerformed(evt);
            }
        });
        jPanel1.add(btn43, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 340, 40, 30));

        btn44.setBackground(java.awt.Color.black);
        btn44.setText("1");
        btn44.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn44.setBorderPainted(false);
        btn44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn44ActionPerformed(evt);
            }
        });
        jPanel1.add(btn44, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 390, 40, 30));

        btn45.setBackground(java.awt.Color.black);
        btn45.setText("1");
        btn45.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn45.setBorderPainted(false);
        btn45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn45ActionPerformed(evt);
            }
        });
        jPanel1.add(btn45, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 420, 40, 30));

        btn46.setBackground(java.awt.Color.black);
        btn46.setText("00");
        btn46.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn46.setBorderPainted(false);
        btn46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn46ActionPerformed(evt);
            }
        });
        jPanel1.add(btn46, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 430, 40, 30));

        btn47.setBackground(java.awt.Color.black);
        btn47.setText("1");
        btn47.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn47.setBorderPainted(false);
        btn47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn47ActionPerformed(evt);
            }
        });
        jPanel1.add(btn47, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 470, 40, 30));

        btn48.setBackground(java.awt.Color.black);
        btn48.setText("1");
        btn48.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn48.setBorderPainted(false);
        btn48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn48ActionPerformed(evt);
            }
        });
        jPanel1.add(btn48, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 510, 40, 30));

        btn49.setBackground(java.awt.Color.black);
        btn49.setText("1");
        btn49.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn49.setBorderPainted(false);
        btn49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn49ActionPerformed(evt);
            }
        });
        jPanel1.add(btn49, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 720, 50, 40));

        btn50.setBackground(java.awt.Color.black);
        btn50.setText("1");
        btn50.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn50.setBorderPainted(false);
        btn50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn50ActionPerformed(evt);
            }
        });
        jPanel1.add(btn50, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 60, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/us.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1190, 820));
        jPanel1.add(btn0, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, -1, 0));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 860));

        armyCountLbl.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        armyCountLbl.setText("Number Of Army To attack:");
        getContentPane().add(armyCountLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 480, 270, 70));

        armyCountTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        armyCountTF.setText("0");
        getContentPane().add(armyCountTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 580, 100, 70));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel4.setText("Current Action:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 310, 160, 70));
        getContentPane().add(currentStateLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 370, 250, 70));

        errorLbl.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        errorLbl.setForeground(java.awt.Color.red);
        errorLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(errorLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 900, 940, 60));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel3.setText("Current Player:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 130, 160, 70));

        currentPlayerlbl.setBackground(java.awt.Color.red);
        currentPlayerlbl.setOpaque(true);
        getContentPane().add(currentPlayerlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 210, 80, 70));

        skipAttackBtn.setText("Skip Attack");
        skipAttackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skipAttackBtnActionPerformed(evt);
            }
        });
        getContentPane().add(skipAttackBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 740, 210, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn50ActionPerformed
        HumanPlaying(btn50);
    }//GEN-LAST:event_btn50ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        HumanPlaying(btn1);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        HumanPlaying(btn2);
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        HumanPlaying(btn3);
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        HumanPlaying(btn4);
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        HumanPlaying(btn5);
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        HumanPlaying(btn6);
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        HumanPlaying(btn7);
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        HumanPlaying(btn8);
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        HumanPlaying(btn9);
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn11ActionPerformed
        HumanPlaying(btn11);
    }//GEN-LAST:event_btn11ActionPerformed

    private void btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10ActionPerformed
        HumanPlaying(btn10);
    }//GEN-LAST:event_btn10ActionPerformed

    private void btn17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn17ActionPerformed
        HumanPlaying(btn17);
    }//GEN-LAST:event_btn17ActionPerformed

    private void btn16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn16ActionPerformed
        HumanPlaying(btn16);
    }//GEN-LAST:event_btn16ActionPerformed

    private void btn15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn15ActionPerformed
        HumanPlaying(btn15);
    }//GEN-LAST:event_btn15ActionPerformed

    private void btn14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn14ActionPerformed
        HumanPlaying(btn14);
    }//GEN-LAST:event_btn14ActionPerformed

    private void btn13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn13ActionPerformed
        HumanPlaying(btn13);
    }//GEN-LAST:event_btn13ActionPerformed

    private void btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn12ActionPerformed
        HumanPlaying(btn12);
    }//GEN-LAST:event_btn12ActionPerformed

    private void btn22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn22ActionPerformed
        HumanPlaying(btn22);
    }//GEN-LAST:event_btn22ActionPerformed

    private void btn21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn21ActionPerformed
        HumanPlaying(btn21);
    }//GEN-LAST:event_btn21ActionPerformed

    private void btn20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn20ActionPerformed
        HumanPlaying(btn20);
    }//GEN-LAST:event_btn20ActionPerformed

    private void btn19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn19ActionPerformed
        HumanPlaying(btn19);
    }//GEN-LAST:event_btn19ActionPerformed

    private void btn18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn18ActionPerformed
        HumanPlaying(btn18);
    }//GEN-LAST:event_btn18ActionPerformed

    private void btn34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn34ActionPerformed
        HumanPlaying(btn34);
    }//GEN-LAST:event_btn34ActionPerformed

    private void btn33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn33ActionPerformed
        HumanPlaying(btn33);
    }//GEN-LAST:event_btn33ActionPerformed

    private void btn23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn23ActionPerformed
        HumanPlaying(btn23);
    }//GEN-LAST:event_btn23ActionPerformed

    private void btn24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn24ActionPerformed
        HumanPlaying(btn24);
    }//GEN-LAST:event_btn24ActionPerformed

    private void btn31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn31ActionPerformed
        HumanPlaying(btn31);
    }//GEN-LAST:event_btn31ActionPerformed

    private void btn32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn32ActionPerformed
        HumanPlaying(btn32);
    }//GEN-LAST:event_btn32ActionPerformed

    private void btn35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn35ActionPerformed
        HumanPlaying(btn35);
    }//GEN-LAST:event_btn35ActionPerformed

    private void btn37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn37ActionPerformed
        HumanPlaying(btn37);
    }//GEN-LAST:event_btn37ActionPerformed

    private void btn36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn36ActionPerformed
        HumanPlaying(btn36);
    }//GEN-LAST:event_btn36ActionPerformed

    private void btn30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn30ActionPerformed
        HumanPlaying(btn30);
    }//GEN-LAST:event_btn30ActionPerformed

    private void btn26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn26ActionPerformed
        HumanPlaying(btn26);
    }//GEN-LAST:event_btn26ActionPerformed

    private void btn27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn27ActionPerformed
        HumanPlaying(btn27);
    }//GEN-LAST:event_btn27ActionPerformed

    private void btn28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn28ActionPerformed
        HumanPlaying(btn28);
    }//GEN-LAST:event_btn28ActionPerformed

    private void btn29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn29ActionPerformed
        HumanPlaying(btn29);
    }//GEN-LAST:event_btn29ActionPerformed

    private void btn38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn38ActionPerformed
        HumanPlaying(btn38);
    }//GEN-LAST:event_btn38ActionPerformed

    private void btn39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn39ActionPerformed
        HumanPlaying(btn39);
    }//GEN-LAST:event_btn39ActionPerformed

    private void btn40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn40ActionPerformed
        HumanPlaying(btn40);
    }//GEN-LAST:event_btn40ActionPerformed

    private void btn41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn41ActionPerformed
        HumanPlaying(btn41);
    }//GEN-LAST:event_btn41ActionPerformed

    private void btn42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn42ActionPerformed
        HumanPlaying(btn42);
    }//GEN-LAST:event_btn42ActionPerformed

    private void btn43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn43ActionPerformed
        HumanPlaying(btn43);
    }//GEN-LAST:event_btn43ActionPerformed

    private void btn44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn44ActionPerformed
        HumanPlaying(btn44);
    }//GEN-LAST:event_btn44ActionPerformed

    private void btn45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn45ActionPerformed
        HumanPlaying(btn45);
    }//GEN-LAST:event_btn45ActionPerformed

    private void btn46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn46ActionPerformed
        HumanPlaying(btn46);
    }//GEN-LAST:event_btn46ActionPerformed

    private void btn47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn47ActionPerformed
        HumanPlaying(btn47);
    }//GEN-LAST:event_btn47ActionPerformed

    private void btn48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn48ActionPerformed
        HumanPlaying(btn48);
    }//GEN-LAST:event_btn48ActionPerformed

    private void btn25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn25ActionPerformed
        HumanPlaying(btn25);
    }//GEN-LAST:event_btn25ActionPerformed

    private void btn49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn49ActionPerformed
        HumanPlaying(btn49);
    }//GEN-LAST:event_btn49ActionPerformed

    private void skipAttackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skipAttackBtnActionPerformed
        skipAttackBtn.setVisible(false);
        armyCountLbl.setVisible(false);
        armyCountTF.setVisible(false);
        updateTerritories();
        currentStateLbl.setText("Player 2's Turn");
        armyCountTF.setText("0");
        GamePlay.getInstance().nextState();
        if (GamePlay.getInstance().currentPlayer().win()) {
            this.setVisible(false);
            Winner frame = new Winner("Human Player Won!");
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        }
        GamePlay.getInstance().changeTurn();
        ComputerPlaying();
        updateTerritories();
        if (GamePlay.getInstance().currentPlayer().win()) {
            this.setVisible(false);
            Winner frame = new Winner(GamePlay.getInstance().currentPlayer().getClass().getSimpleName() + "Player Won!");
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        }
        GamePlay.getInstance().changeTurn();
        updateTerritories();
        skipAttackBtn.setVisible(false);
        additional_armies = GamePlay.getInstance().currentPlayer().calculateAdditionalArmies();
        GamePlay.getInstance().nextState();
        currentStateLbl.setText("Place Armies: " + String.valueOf(additional_armies));
    }//GEN-LAST:event_skipAttackBtnActionPerformed

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
            java.util.logging.Logger.getLogger(USMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(USMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(USMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(USMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new USMap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel armyCountLbl;
    private javax.swing.JTextField armyCountTF;
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn13;
    private javax.swing.JButton btn14;
    private javax.swing.JButton btn15;
    private javax.swing.JButton btn16;
    private javax.swing.JButton btn17;
    private javax.swing.JButton btn18;
    private javax.swing.JButton btn19;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn20;
    private javax.swing.JButton btn21;
    private javax.swing.JButton btn22;
    private javax.swing.JButton btn23;
    private javax.swing.JButton btn24;
    private javax.swing.JButton btn25;
    private javax.swing.JButton btn26;
    private javax.swing.JButton btn27;
    private javax.swing.JButton btn28;
    private javax.swing.JButton btn29;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn30;
    private javax.swing.JButton btn31;
    private javax.swing.JButton btn32;
    private javax.swing.JButton btn33;
    private javax.swing.JButton btn34;
    private javax.swing.JButton btn35;
    private javax.swing.JButton btn36;
    private javax.swing.JButton btn37;
    private javax.swing.JButton btn38;
    private javax.swing.JButton btn39;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn40;
    private javax.swing.JButton btn41;
    private javax.swing.JButton btn42;
    private javax.swing.JButton btn43;
    private javax.swing.JButton btn44;
    private javax.swing.JButton btn45;
    private javax.swing.JButton btn46;
    private javax.swing.JButton btn47;
    private javax.swing.JButton btn48;
    private javax.swing.JButton btn49;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn50;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JLabel currentPlayerlbl;
    private javax.swing.JLabel currentStateLbl;
    private javax.swing.JLabel errorLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton skipAttackBtn;
    // End of variables declaration//GEN-END:variables
}
