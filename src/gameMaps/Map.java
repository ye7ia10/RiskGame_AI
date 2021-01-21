/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameMaps;

import agents.Agent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import riskgame.GamePlay;
import riskgame.State;

/**
 *
 * @author mostafanabil198
 */
public abstract class Map {
    public ArrayList<Territory> territories = new ArrayList<>();
    public HashMap<Integer, ArrayList<Integer>> neighbours = new HashMap<>();
    
    public ArrayList<Integer> getNeighbours(int territoryNumber){
        return neighbours.get(territoryNumber);
    }
    
    public Territory getTerritory(int territoryNumber){
        return territories.get(territoryNumber);
    }
    
    public ArrayList<Territory> getAgentTerritories(Agent player){
        ArrayList<Territory> agentTerritories = new ArrayList<>();
        for(Territory t : territories){
            if(t.getOwner() == player){
                agentTerritories.add(t);
            }
        }
        return agentTerritories;
    }
    
    public boolean agentHasTerritory(Agent player, int territoryNumber){
        return territories.get(territoryNumber).getOwner() == player;
    }
    
    public boolean isNeighbours(int territoryNumber1, int territoryNumber2){
        return getNeighbours(territoryNumber1).contains(territoryNumber2);
    }
    
    public boolean isOpponentNeighbours(Agent player, int territoryNumber1, int territoryNumber2){
        return isNeighbours(territoryNumber1, territoryNumber2) && !agentHasTerritory(player, territoryNumber2);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public State getCurrentState(char myChar, Agent owner) {
        StringBuilder myBuild = new StringBuilder();
        ArrayList<Integer> armyCount = new ArrayList<Integer>();
        armyCount.add(0);
        myBuild.append('#');
        char enemyChar = '1';
        if (myChar == '1') {
            enemyChar = '0';
        }
        for (int i = 1; i < territories.size(); i++) {
            if (territories.get(i).getOwner().equals(owner)) {
                myBuild.append(myChar);
            } else {
                myBuild.append(enemyChar);
            }
            armyCount.add(territories.get(i).getArmiesCount());
        }
        return new State(myBuild.toString(), armyCount, myChar);
    }
    
    public void setState(State state, char attackerChar, Agent attacker) {
        for (int i = 1; i < territories.size(); i++) {
            if (state.getTeritoriesOwners().charAt(i) == attackerChar) {
                territories.get(i).setOwner(attacker);
            } 
            territories.get(i).setArmiesCount(state.getArmyCount().get(i));
        }
    }
    
}
