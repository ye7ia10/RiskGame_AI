/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import gameMaps.Territory;
import java.util.ArrayList;
import riskgame.GamePlay;

/**
 *
 * @author mostafanabil198
 */
public class PassivePlayer extends Agent{
    @Override
    public void play(){
        System.err.println("======PASSIVE PLAYED");
        int bonusArmies = this.calculateAdditionalArmies(); 
        ArrayList<Territory> territories = GamePlay.getInstance().getGameMap().getAgentTerritories(this);
        int minArmy = Integer.MAX_VALUE;
        int territoryNumber = 0;
        for (int i = 0 ; i < territories.size(); i++){
            int armyCount = territories.get(i).getArmiesCount();
            if ( armyCount < minArmy){
                minArmy = armyCount;
                territoryNumber = territories.get(i).getTerritoryNumber();
            } 
        }
        int newArmy = minArmy + bonusArmies;
        GamePlay.getInstance().getGameMap().getTerritory(territoryNumber).setArmiesCount(newArmy);
        System.err.println("=======PASSIVE ENDDDDD");
    }
    
 
}
