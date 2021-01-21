/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import gameMaps.Territory;
import riskgame.GamePlay;

/**
 *
 * @author mostafanabil198
 */
public class HumanPlayer extends Agent{
 
    @Override
    public boolean placeArmies(int territoryNumber){
        Territory t = GamePlay.getInstance().getGameMap().getTerritory(territoryNumber);
        if(t.getOwner() == this){
            t.setArmiesCount(t.getArmiesCount() + 1);
            return true;
        }
        return false;
    }
    
    @Override
    public void attack(int attackingTerritory, int targetTerritory, int armiesCount){
         Territory t1 = GamePlay.getInstance().getGameMap().getTerritory(attackingTerritory);
         Territory t2 = GamePlay.getInstance().getGameMap().getTerritory(targetTerritory);
         
         if(armiesCount > t2.getArmiesCount()){
             t1.setArmiesCount(t1.getArmiesCount() - armiesCount);
             t2.setArmiesCount(armiesCount - t2.getArmiesCount());
             t2.setOwner(t1.getOwner());
         } else {
             t1.setArmiesCount(t1.getArmiesCount() - armiesCount);
             t2.setArmiesCount(t2.getArmiesCount() - armiesCount);
         }
    }
}
