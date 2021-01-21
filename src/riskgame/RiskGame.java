/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskgame;

import GUI.MainMenu;
import GUI.attack;
import agents.Agent;
import agents.HumanPlayer;
import agents.PassivePlayer;
import gameMaps.Map;
import gameMaps.USMap;
import java.util.Random;

/**
 *
 * @author mostafanabil198
 */
public class RiskGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//       Map map = new USMap();
//       Agent players[] = {new PassivePlayer(), new HumanPlayer()};
       GamePlay gamePlay = GamePlay.getInstance();
//       gamePlay.setGameMap(map);
//       gamePlay.setPlayers(players);
//       gamePlay.assignTerritories();
        MainMenu menu = new MainMenu();
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        
        //attack a = new attack();
        //a.setVisible(true);
    }
    
}
