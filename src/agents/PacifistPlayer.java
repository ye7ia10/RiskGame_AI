
package agents;

import gameMaps.Territory;
import java.util.ArrayList;
import riskgame.GamePlay;


public class PacifistPlayer extends Agent{
    
    @Override
    public boolean placeArmies(int territoryNumber){
        int bonous = this.calculateAdditionalArmies(); /** get the additional armies **/
        ArrayList<Territory> territories = GamePlay.getInstance().getGameMap().getAgentTerritories(this);
        int minArmy = Integer.MAX_VALUE;
        int minIdx = 0;
        for (int i = 0 ; i < territories.size(); i++){
            int armyCount = territories.get(i).getArmiesCount();
            if ( armyCount < minArmy){
                minArmy = armyCount;
                minIdx = i;
            } 
        }
        int newArmy = minArmy + bonous;
        System.out.println("Placed " + newArmy + "  in  " + minIdx);
        GamePlay.getInstance().getGameMap().getAgentTerritories(this).get(minIdx).setArmiesCount(newArmy);
        return true;
    }
    
    @Override
    public void attack(int attackingTerritory, int targetTerritory, int armiesCount){
       ArrayList<Territory> territories = GamePlay.getInstance().getGameMap().getAgentTerritories(this);
       int minTargetArmy = Integer.MAX_VALUE;
       int minTargetNumber = 0;
       int attackTerr = 0;
       int attackerID = 0;
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
                   if (enemyArmy < minTargetArmy){
                       minTargetArmy = enemyArmy;
                       minTargetNumber = eneTerritory.getTerritoryNumber();
                       if (currArmy-1 > enemyArmy){
                           possible = true;
                           attackerArmy = currArmy;
                           attackerID = i;
                           attackTerr = currentTerr.getTerritoryNumber();
                       } else {
                           possible = false;
                       }
                   }
               }
           }
       }
       
       /** check if possible attack **/
       if (possible){
           Territory eneTerritory = GamePlay.getInstance().getGameMap().getTerritory(minTargetNumber);
           eneTerritory.setOwner(this);
           eneTerritory.setArmiesCount(1);
           System.out.println("Attacked " + minTargetNumber + "  by  " + (minTargetArmy + 1));
           GamePlay.getInstance().getGameMap().getTerritory(attackTerr).setArmiesCount(attackerArmy - minTargetArmy - 1);
       }
    }
    
    @Override
    public void play(){
        placeArmies(0);
        attack(0, 0, 0);
    }

}