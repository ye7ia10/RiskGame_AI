/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import riskgame.GamePlay;
import riskgame.State;

/**
 *
 * @author mostafanabil198
 */
public class GreedyPlayer extends Agent{

     @Override
    public void play() {
        System.out.println("Greeeeeeeeeeedy Start");
        char playerChar = '1';
        PriorityQueue<State> frontier = new PriorityQueue<>();
        State currentState = GamePlay.getInstance().getGameMap().getCurrentState(playerChar, this);
        currentState.setPlayerType(0);
        //System.out.println(currentState.getNumberOfAttackerCities() + " size of attacks");
        ArrayList<State> children = currentState.getChildrensv2();
        for(State st: children){
            ArrayList<State> attacks = st.getAttacks();
            for(State att: attacks){
             frontier.add(att);   
            } 
        }
        State best = frontier.poll();
        
        GamePlay.getInstance().getGameMap().setState(best, playerChar, this);
        System.out.println("Greeeeeeeeeeedy End");
    }
    
}
