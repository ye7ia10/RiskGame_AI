/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskgame;

import agents.Agent;
import gameMaps.Map;
import gameMaps.Territory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author mostafanabil198
 */
public class GamePlay {
    private static GamePlay gameplay = null;
    private Agent[] players;
    private Map gameMap;
    private int turn;
    private String currentPlayerState; // additional_armies - place_armies - attack
    
    public String getCurrentPlayerState(){
        return currentPlayerState;
    }
    
    public void nextState(){
        if(currentPlayerState == "additional_armies"){
            currentPlayerState = "place_armies";
        } else if(currentPlayerState == "place_armies"){
            currentPlayerState = "attack";
        } else {
            currentPlayerState = "additional_armies";
        }
    }
    
    public Agent[] getPlayers() {
        return players;
    }

    public void setPlayers(Agent[] players) {
        this.players = players;
    }

    public Map getGameMap() {
        return gameMap;
    }

    public void setGameMap(Map gameMap) {
        this.gameMap = gameMap;
    }

    public int getTurn() {
        return turn;
    }
    
    public Agent currentPlayer(){
        return players[turn];
    }

    public void changeTurn(){
        turn = 1- turn;
        players[turn].IncreaseTurnsCount();
    }
        
    private GamePlay() {
        turn = 0;
        currentPlayerState = "additional_armies";
    }
    
    public static GamePlay getInstance() 
    { 
        if (gameplay == null) 
            gameplay = new GamePlay(); 
  
        return gameplay; 
    } 
    
    

    
    
    public void assignTerritories(){
        String x = "";
        ArrayList<Integer> territories = new ArrayList<>();
        int player = 0;
        for(int i = 1; i < gameMap.territories.size(); i++){
            territories.add(i);
        }
        
        for(int i = 1; i < gameMap.territories.size(); i++){
            Random rand = new Random();
            int p = rand.nextInt(territories.size());
            gameMap.getTerritory(territories.get(p)).setOwner(players[player]);
            gameMap.getTerritory(territories.get(p)).setArmiesCount(1);
            territories.remove(p);
            player = 1-player;
        }
        
        //-- if map size < 40 add the rest of counts randomly
        if(gameMap.territories.size() == 28){ 
            for(int i = 1; i < gameMap.territories.size(); i++){
                territories.add(i);
            }
            for(int i = 1; i < 14; i++){
                Random rand = new Random();
                int p = rand.nextInt(gameMap.getAgentTerritories(players[player]).size());
                gameMap.getAgentTerritories(players[player]).get(p).setArmiesCount(gameMap.getAgentTerritories(players[player]).get(p).getArmiesCount() + 1);
                player = 1-player;
            }
        }
        ArrayList<Integer>army = new  ArrayList<>();
        for(Territory t : gameMap.territories){
            x+= players[0] == t.getOwner() ? "0" : "1";
            army.add(t.getArmiesCount());
        }
        System.out.println(x);
        System.out.println(army);
//         set the state manually**********************************8
//        String s = "1001010000001111111100001011";
//        int a[] = {0, 2, 2, 1, 3, 1, 2, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1, 3, 1, 1};
//        int idx = 0;
//        for (Territory t : gameMap.territories) {
//            t.setOwner( players[Integer.parseInt(String.valueOf(s.charAt(idx)))]);
//            t.setArmiesCount(a[idx]);
//            idx++;
//        }
    }
    
}
