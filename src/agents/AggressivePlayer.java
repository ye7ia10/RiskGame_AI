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
public class AggressivePlayer extends Agent{
    @Override
    public void play(){
        // add bonus
        System.out.println("Aggressive start");
        int bonusArmies = this.calculateAdditionalArmies(); 
        ArrayList<Territory> territories = GamePlay.getInstance().getGameMap().getAgentTerritories(this);
        int maxArmy = Integer.MIN_VALUE;
        int territoryNumber = 0;
        for (int i = 0 ; i < territories.size(); i++){
            int armyCount = territories.get(i).getArmiesCount();
            if ( armyCount > maxArmy){
                maxArmy = armyCount;
                territoryNumber = territories.get(i).getTerritoryNumber();
            } 
        }
        
        if (maxArmy == Integer.MIN_VALUE) return; // player lose already
    
        int newArmy = maxArmy + bonusArmies;
        GamePlay.getInstance().getGameMap().getTerritory(territoryNumber).setArmiesCount(newArmy);
        
        // attack
       int maxTargetArmy = Integer.MIN_VALUE;
       int maxTargetNumber = 0;
       int attackerID = 0;
       int attackerTerr = 0;
       int attackerArmy = 0;
       boolean possible = false;
       for (int i = 0; i < territories.size() ; i++){
           Territory currentTerr = territories.get(i);
           int currArmy = currentTerr.getArmiesCount();
           ArrayList<Integer> neighbours = GamePlay.getInstance().getGameMap().getNeighbours(currentTerr.getTerritoryNumber());
           for (int j = 0; j  < neighbours.size() ; j++){
               Territory eneTerritory = GamePlay.getInstance().getGameMap().getTerritory(neighbours.get(j));
               int enemyArmy = eneTerritory.getArmiesCount();
               if (!currentTerr.getOwner().equals(eneTerritory.getOwner())){
                   if (enemyArmy > maxTargetNumber && currArmy - 1 > enemyArmy){
                       maxTargetArmy = enemyArmy;
                       maxTargetNumber = eneTerritory.getTerritoryNumber();
                       possible = true;
                       attackerArmy = currArmy;
                       attackerID = i;
                       attackerTerr = currentTerr.getTerritoryNumber();
                   }
               }
           }
       }
       
       /** check if possible attack **/
       if (possible){
           Territory eneTerritory = GamePlay.getInstance().getGameMap().getTerritory(maxTargetNumber);
           eneTerritory.setOwner(this);
           eneTerritory.setArmiesCount(attackerArmy - 1 - maxTargetArmy);
//           GamePlay.getInstance().getGameMap().getAgentTerritories(this).add(eneTerritory);
           //GamePlay.getInstance().getGameMap().getAgentTerritories(this).get(attackerID).setArmiesCount(1);
           GamePlay.getInstance().getGameMap().getTerritory(attackerTerr).setArmiesCount(1);
       }
       System.out.println("Aggressive end");
    }
}
