/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import java.awt.Color;
import riskgame.GamePlay;

/**
 *
 * @author mostafanabil198
 */
public abstract class Agent {
    private Color color;
    private int turnsCount = 0;
    public int expansionSteps = 0;

    public int getTurnsCount() {
        return turnsCount;
    }

    public void IncreaseTurnsCount() {
        this.turnsCount++;
    }
    
    public int getExpansionSteps() {
        return expansionSteps;
    }

    public void IncreaseExpansionStepsBy(int expansionSteps) {
        this.expansionSteps+= expansionSteps;
    }
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public int calculateAdditionalArmies(){
        return Math.max(GamePlay.getInstance().getGameMap().getAgentTerritories(this).size()/3, 3);
    }
    
    public boolean placeArmies(int territoryNumber){
        return false;
    }
    
    public void attack(int attackingTerritory, int targetTerritory, int armiesCount){
         
    }
    
    public void play(){
        
    }
    
    public boolean win(){
        return GamePlay.getInstance().getGameMap().getAgentTerritories(this).size() == GamePlay.getInstance().getGameMap().territories.size() - 1;
    }
    
}
