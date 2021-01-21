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
import javax.swing.JFrame;
import javax.swing.Timer;
import riskgame.GamePlay;

/**
 *
 * @author mostafanabil198
 */
public class EgyptMap extends  Map{
    ArrayList<JButton> territoryBtns = new ArrayList<>();
    int play;
    int additional_armies;
    boolean attacker;
    int attackerTerritory = -1;
    /**
     * Creates new form EgyptMap
     */
    
    public EgyptMap(int play){
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
        armyCountTF.setVisible(false);
        armyCountLbl.setVisible(false);
        skipAttackBtn.setVisible(false);
        
        updateTerritories();
        currentPlayerlbl.setBackground(GamePlay.getInstance().getPlayers()[0].getColor());
        if(play == 2){
            TIMER.start();
        } else{// if(play == 2) or not
            additional_armies = GamePlay.getInstance().currentPlayer().calculateAdditionalArmies();
            currentStateLbl.setText("Place Armies: " + String.valueOf(additional_armies));
            GamePlay.getInstance().nextState();
        }
    }
    public EgyptMap() {
        initComponents();
    }
    
    public void updateTerritories(){
        ArrayList<Territory> territories = GamePlay.getInstance().getGameMap().territories;
        for(int i = 1; i <= 27; i++){
            if(territories.get(i).getOwner() != null){
                territoryBtns.get(i).setBackground(territories.get(i).getOwner().getColor());
            }
            territoryBtns.get(i).setText(String.valueOf(territories.get(i).getArmiesCount()));
        }
        currentPlayerlbl.setBackground(GamePlay.getInstance().currentPlayer().getColor());
    }
    
//    private void humanVsComp(){
//        GamePlay.getInstance().changeTurn();
//        while(!GamePlay.getInstance().currentPlayer().win()){
//            GamePlay.getInstance().changeTurn();
//            if("HumanPlayer".equals(GamePlay.getInstance().currentPlayer().getClass().getSimpleName())){
//                HumanPlaying();
//            } else {
//                ComputerPlaying();
//            }
//            updateTerritories();
//        }
//        //-- Winner Frame should appear
//    }
    
    public final Timer TIMER = new Timer(1500,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
         GamePlay.getInstance().changeTurn();
         updateTerritories();
         System.out.println("*****Changed Played*****");
         ComputerPlaying();
//         updateTerritories();
         //System.out.println(GamePlay.getInstance().currentPlayer().win());
         if(GamePlay.getInstance().currentPlayer().win()){
            TIMER.stop();
            exitFrame();
            Winner frame = new Winner(GamePlay.getInstance().currentPlayer().getClass().getSimpleName() + "Player Won!");
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
         }
        }
    });
    
    private void exitFrame(){
        this.setVisible(false);
    }
    
//    private void compVsComp(){
//        System.out.println("1");
//        GamePlay.getInstance().changeTurn();
//        System.out.println("2");
//        while(!GamePlay.getInstance().currentPlayer().win()){
//            System.out.println("3");
//            GamePlay.getInstance().changeTurn();
//            System.out.println("4");
//            ComputerPlaying();
//            System.out.println("5");
//            updateTerritories();
//            System.out.println("6");
//        }
//        //-- Winner Frame should appear
//    }
    
    private void ComputerPlaying(){
        GamePlay.getInstance().currentPlayer().play();
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
    
    private void HumanPlaying(JButton btn){
        errorLbl.setText("");
        armyCountTF.setVisible(false);
        armyCountLbl.setVisible(false);
        System.out.println(GamePlay.getInstance().currentPlayer().getClass().getSimpleName());
        System.out.println(GamePlay.getInstance().getCurrentPlayerState());
        if("HumanPlayer".equals(GamePlay.getInstance().currentPlayer().getClass().getSimpleName())){
            if(GamePlay.getInstance().getCurrentPlayerState() == "place_armies"){
                System.out.println("PLACE ARMIES");
                if(GamePlay.getInstance().currentPlayer().placeArmies(territoryBtns.indexOf(btn))){
                    System.out.println("TRUE IN" + territoryBtns.indexOf(btn));
                    additional_armies -= 1;
                    updateTerritories();
                    currentStateLbl.setText("Place Armies: " + String.valueOf(additional_armies));
                    if(additional_armies == 0){
                        GamePlay.getInstance().nextState();
                        currentStateLbl.setText("Select Attacker Territory");
                        skipAttackBtn.setVisible(true);
                    }
                }
            } else if(GamePlay.getInstance().getCurrentPlayerState() == "attack"){
                if(attacker && GamePlay.getInstance().getGameMap().agentHasTerritory(GamePlay.getInstance().currentPlayer(), territoryBtns.indexOf(btn))){
                    if(GamePlay.getInstance().getGameMap().getTerritory(territoryBtns.indexOf(btn)).getArmiesCount() > 1){
                        attacker = false;
                        attackerTerritory = territoryBtns.indexOf(btn);
                        btn.setBackground(Color.RED);
                        currentStateLbl.setText("Select Territory To Attack On");
                        armyCountTF.setVisible(true);
                        armyCountLbl.setVisible(true);
                    } else {
                        errorLbl.setText("Can't attack by this territory as it has only 1 army");
                        updateTerritories();
                    }
                } else if(!attacker){
                    if(GamePlay.getInstance().getGameMap().isOpponentNeighbours(GamePlay.getInstance().currentPlayer(), attackerTerritory, territoryBtns.indexOf(btn))){
                        if(Integer.parseInt(armyCountTF.getText()) > 1 && Integer.parseInt(armyCountTF.getText()) < GamePlay.getInstance().getGameMap().getTerritory(attackerTerritory).getArmiesCount()){
                            if(play == 3)
                            {
                                attack a = new attack(this,attackerTerritory, territoryBtns.indexOf(btn), Integer.parseInt(armyCountTF.getText()));
                                a.setVisible(true);
                               
                            }else{
                            GamePlay.getInstance().currentPlayer().attack(attackerTerritory, territoryBtns.indexOf(btn), Integer.parseInt(armyCountTF.getText()));
                            //open new frame
                            updateTerritories();
                            currentStateLbl.setText("Player 2's Turn");
                            armyCountTF.setText("0");
                            GamePlay.getInstance().nextState();
                            if(GamePlay.getInstance().currentPlayer().win()){
                                this.setVisible(false);
                                Winner frame = new Winner("Human Player Won!");
                                frame.setVisible(true);
                                frame.setLocationRelativeTo(null);
                            }
                            GamePlay.getInstance().changeTurn();
                            ComputerPlaying();
                            updateTerritories();
                            if(GamePlay.getInstance().currentPlayer().win()){
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
                            errorLbl.setText("Can't Attack With 1 >= Armies >= Your Total Armies");
                            updateTerritories();
                        }
                    } else {
                        errorLbl.setText("Can't attack on this territory as it's either friend territory or not neighbour territor");
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
        btn14 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();
        btn24 = new javax.swing.JButton();
        btn25 = new javax.swing.JButton();
        btn27 = new javax.swing.JButton();
        btn26 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        btn18 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn19 = new javax.swing.JButton();
        btn15 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn17 = new javax.swing.JButton();
        btn16 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn0 = new javax.swing.JButton();
        armyCountLbl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        currentPlayerlbl = new javax.swing.JLabel();
        currentStateLbl = new javax.swing.JLabel();
        errorLbl = new javax.swing.JLabel();
        armyCountTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        skipAttackBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1400, 1000));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn1.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn1.setText("0");
        btn1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn1.setBorderPainted(false);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 50, 40));

        btn2.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn2.setText("1");
        btn2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn2.setBorderPainted(false);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        jPanel1.add(btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 40, 30));

        btn14.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn14.setText("0");
        btn14.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn14.setBorderPainted(false);
        btn14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn14ActionPerformed(evt);
            }
        });
        jPanel1.add(btn14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 40, 40));

        btn20.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn20.setText("1");
        btn20.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn20.setBorderPainted(false);
        btn20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn20ActionPerformed(evt);
            }
        });
        jPanel1.add(btn20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 40, 40));

        btn21.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn21.setText("1");
        btn21.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn21.setBorderPainted(false);
        btn21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn21ActionPerformed(evt);
            }
        });
        jPanel1.add(btn21, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 520, 40, 40));

        btn22.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn22.setText("1");
        btn22.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn22.setBorderPainted(false);
        btn22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn22ActionPerformed(evt);
            }
        });
        jPanel1.add(btn22, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, 40, 30));

        btn24.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn24.setText("1");
        btn24.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn24.setBorderPainted(false);
        btn24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn24ActionPerformed(evt);
            }
        });
        jPanel1.add(btn24, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 400, 40, 30));

        btn25.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn25.setText("1");
        btn25.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn25.setBorderPainted(false);
        btn25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn25ActionPerformed(evt);
            }
        });
        jPanel1.add(btn25, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 440, 40, 30));

        btn27.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn27.setText("1");
        btn27.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn27.setBorderPainted(false);
        btn27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn27ActionPerformed(evt);
            }
        });
        jPanel1.add(btn27, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 630, 40, 30));

        btn26.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn26.setText("1");
        btn26.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn26.setBorderPainted(false);
        btn26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn26ActionPerformed(evt);
            }
        });
        jPanel1.add(btn26, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 460, 40, 30));

        btn23.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn23.setText("1");
        btn23.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn23.setBorderPainted(false);
        btn23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn23ActionPerformed(evt);
            }
        });
        jPanel1.add(btn23, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 350, 40, 30));

        btn18.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn18.setText("1");
        btn18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn18.setBorderPainted(false);
        btn18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn18ActionPerformed(evt);
            }
        });
        jPanel1.add(btn18, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 210, 40, 30));

        btn8.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn8.setText("1");
        btn8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn8.setBorderPainted(false);
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        jPanel1.add(btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, 40, 30));

        btn7.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn7.setText("1");
        btn7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn7.setBorderPainted(false);
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        jPanel1.add(btn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 40, 30));

        btn6.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn6.setText("1");
        btn6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn6.setBorderPainted(false);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        jPanel1.add(btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 40, 30));

        btn19.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn19.setText("1");
        btn19.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn19.setBorderPainted(false);
        btn19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn19ActionPerformed(evt);
            }
        });
        jPanel1.add(btn19, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 40, 30));

        btn15.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn15.setText("1");
        btn15.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn15.setBorderPainted(false);
        btn15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn15ActionPerformed(evt);
            }
        });
        jPanel1.add(btn15, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, 40, 30));

        btn13.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn13.setText("1");
        btn13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn13.setBorderPainted(false);
        btn13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn13ActionPerformed(evt);
            }
        });
        jPanel1.add(btn13, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 40, 30));

        btn17.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn17.setText("1");
        btn17.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn17.setBorderPainted(false);
        btn17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn17ActionPerformed(evt);
            }
        });
        jPanel1.add(btn17, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 40, 30));

        btn16.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn16.setText("1");
        btn16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn16.setBorderPainted(false);
        btn16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn16ActionPerformed(evt);
            }
        });
        jPanel1.add(btn16, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 180, 40, 30));

        btn11.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn11.setText("1");
        btn11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn11.setBorderPainted(false);
        btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn11ActionPerformed(evt);
            }
        });
        jPanel1.add(btn11, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 40, 30));

        btn10.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn10.setText("1");
        btn10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn10.setBorderPainted(false);
        btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10ActionPerformed(evt);
            }
        });
        jPanel1.add(btn10, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 40, 30));

        btn4.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn4.setText("1");
        btn4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn4.setBorderPainted(false);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        jPanel1.add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 40, 40));

        btn9.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn9.setText("1");
        btn9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn9.setBorderPainted(false);
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        jPanel1.add(btn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 30, 30));

        btn12.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn12.setText("1");
        btn12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn12.setBorderPainted(false);
        btn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn12ActionPerformed(evt);
            }
        });
        jPanel1.add(btn12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 30, 30));

        btn5.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn5.setText("1");
        btn5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn5.setBorderPainted(false);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        jPanel1.add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 40, 30));

        btn3.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn3.setText("1");
        btn3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn3.setBorderPainted(false);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        jPanel1.add(btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 40, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/egypt.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 730, 760));

        btn0.setEnabled(false);
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });
        jPanel1.add(btn0, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, 10));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 790));

        armyCountLbl.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        armyCountLbl.setText("Number Of Army To attack:");
        getContentPane().add(armyCountLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 230, 270, 70));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel3.setText("Current Player:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 850, 160, 70));

        currentPlayerlbl.setBackground(java.awt.Color.red);
        currentPlayerlbl.setOpaque(true);
        getContentPane().add(currentPlayerlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 850, 80, 70));
        getContentPane().add(currentStateLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 850, 260, 70));

        errorLbl.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        errorLbl.setForeground(java.awt.Color.red);
        errorLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(errorLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 790, 940, 60));

        armyCountTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        armyCountTF.setText("0");
        getContentPane().add(armyCountTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 230, 100, 70));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel4.setText("Current Action:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 850, 160, 70));

        skipAttackBtn.setText("Skip Attack");
        skipAttackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skipAttackBtnActionPerformed(evt);
            }
        });
        getContentPane().add(skipAttackBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 480, 210, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        HumanPlaying(btn1);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        HumanPlaying(btn2);
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn14ActionPerformed
        HumanPlaying(btn14);
    }//GEN-LAST:event_btn14ActionPerformed

    private void btn20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn20ActionPerformed
        HumanPlaying(btn20);
    }//GEN-LAST:event_btn20ActionPerformed

    private void btn21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn21ActionPerformed
        HumanPlaying(btn21);
    }//GEN-LAST:event_btn21ActionPerformed

    private void btn22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn22ActionPerformed
        HumanPlaying(btn22);
    }//GEN-LAST:event_btn22ActionPerformed

    private void btn24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn24ActionPerformed
        HumanPlaying(btn24);
    }//GEN-LAST:event_btn24ActionPerformed

    private void btn25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn25ActionPerformed
        HumanPlaying(btn25);
    }//GEN-LAST:event_btn25ActionPerformed

    private void btn27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn27ActionPerformed
        HumanPlaying(btn27);
    }//GEN-LAST:event_btn27ActionPerformed

    private void btn26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn26ActionPerformed
        HumanPlaying(btn26);
    }//GEN-LAST:event_btn26ActionPerformed

    private void btn23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn23ActionPerformed
        HumanPlaying(btn23);
    }//GEN-LAST:event_btn23ActionPerformed

    private void btn18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn18ActionPerformed
        HumanPlaying(btn18);
    }//GEN-LAST:event_btn18ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        HumanPlaying(btn8);
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        HumanPlaying(btn7);
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        HumanPlaying(btn6);
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn19ActionPerformed
        HumanPlaying(btn19);
    }//GEN-LAST:event_btn19ActionPerformed

    private void btn15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn15ActionPerformed
        HumanPlaying(btn15);
    }//GEN-LAST:event_btn15ActionPerformed

    private void btn13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn13ActionPerformed
        HumanPlaying(btn13);
    }//GEN-LAST:event_btn13ActionPerformed

    private void btn17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn17ActionPerformed
        HumanPlaying(btn17);
    }//GEN-LAST:event_btn17ActionPerformed

    private void btn16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn16ActionPerformed
        HumanPlaying(btn16);
    }//GEN-LAST:event_btn16ActionPerformed

    private void btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn11ActionPerformed
        HumanPlaying(btn11);
    }//GEN-LAST:event_btn11ActionPerformed

    private void btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10ActionPerformed
        HumanPlaying(btn10);
    }//GEN-LAST:event_btn10ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        HumanPlaying(btn4);
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        HumanPlaying(btn9);
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn12ActionPerformed
        HumanPlaying(btn12);
    }//GEN-LAST:event_btn12ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        HumanPlaying(btn5);
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        HumanPlaying(btn3);
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        HumanPlaying(btn0);
    }//GEN-LAST:event_btn0ActionPerformed

    private void skipAttackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skipAttackBtnActionPerformed
        skipAttackBtn.setVisible(false);
        armyCountLbl.setVisible(false);
        armyCountTF.setVisible(false);
        updateTerritories();
        currentStateLbl.setText("Player 2's Turn");
        armyCountTF.setText("0");
        GamePlay.getInstance().nextState();
        if(GamePlay.getInstance().currentPlayer().win()){
            this.setVisible(false);
            Winner frame = new Winner("Human Player Won!");
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        }
        GamePlay.getInstance().changeTurn();
        ComputerPlaying();
        updateTerritories();
        if(GamePlay.getInstance().currentPlayer().win()){
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
            java.util.logging.Logger.getLogger(EgyptMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EgyptMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EgyptMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EgyptMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EgyptMap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel armyCountLbl;
    public javax.swing.JTextField armyCountTF;
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
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JLabel currentPlayerlbl;
    public javax.swing.JLabel currentStateLbl;
    private javax.swing.JLabel errorLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JButton skipAttackBtn;
    // End of variables declaration//GEN-END:variables
}
