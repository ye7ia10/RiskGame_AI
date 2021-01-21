/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import java.util.ArrayList;
import java.util.Collections;
import riskgame.GamePlay;
import riskgame.State;
import riskgame.StatePair;

/**
 *
 * @author mostafanabil198
 */
public class MinMaxPlayer extends Agent{
    private final int MaxDepth = 3;
    
    @Override
    public void play() {
        
        System.out.println("MINIMAX try to play");
        char playerChar = '1';
        State currentState = GamePlay.getInstance().getGameMap().getCurrentState(playerChar, this);
        currentState.setPlayerType(1);
        ArrayList<State>  children = currentState.getChildrensv2(); // possible steps that can done
        ArrayList<State> neighbors = new ArrayList<>();
        for(State st: children){
            ArrayList<State> attacks = st.getAttacks();
            for(State att: attacks){
                   neighbors.add(att);   
            } 
        }
        Collections.sort(neighbors);
        
        State bestState = null;
        int number_of_best = Integer.MIN_VALUE;
        GamePlay.getInstance().currentPlayer().IncreaseExpansionStepsBy(neighbors.size());
        int idx = 0;
        for (State state : neighbors) {
            idx++;
            if (idx > 5) break;
            StatePair retStatePair = minimize(state, '0', 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            State retState = retStatePair.getFirst();
            int utility = retStatePair.getSecond();
            retState.setAttackerChar('1');
            if (number_of_best < utility) {
                bestState = state;
                number_of_best = utility;
            }
        }
        
        System.out.println("MINIMAX Endeddddddddd");
//        System.out.println(bestState.getTeritoriesOwners() + " " + number_of_best);
        GamePlay.getInstance().getGameMap().setState(bestState, playerChar, this);
//        System.out.println("Played");
    }

    
    private StatePair maximize(State currentState, char playerChar, int depth, int alpha, int beta){
        if (depth == this.MaxDepth || currentState.getNumberOfAttackerCities() == currentState.getTeritoriesOwners().length() - 1 || currentState.getNumberOfAttackerCities() == 0) {
            currentState.setAttackerChar('1');
            return new StatePair(currentState, currentState.getGain());
        }
        State maxState = null;
        int max_utility = Integer.MIN_VALUE;
        currentState.setAttackerChar('1');
        ArrayList<State>  children = currentState.getChildrensv2(); // possible steps that can done
        ArrayList<State> neighbors = new ArrayList<>();
        for(State st: children){
            ArrayList<State> attacks = st.getAttacks();
            for(State att: attacks){
                   neighbors.add(att);   
            } 
        }
        GamePlay.getInstance().currentPlayer().IncreaseExpansionStepsBy(neighbors.size());
        Collections.sort(neighbors);
        int idx = 0;
        for (State state : neighbors) {
            idx++;
            if (idx > 1) break;
            state.setAttackerChar('0');
            StatePair retStatePair = minimize(state, '0', depth + 1, alpha, beta);
            State retState = retStatePair.getFirst();
            int utility = retStatePair.getSecond();
            retState.setAttackerChar('1');
            if (max_utility < utility) {
                maxState = retState;
                max_utility = utility;
            }
            if (max_utility >= beta) break;
            if (max_utility > alpha) alpha = max_utility;
        }
        if (maxState == null) maxState = currentState;
        return new StatePair(maxState, max_utility);
    }
    
    private StatePair minimize(State currentState, char playerChar, int depth, int alpha, int beta){
        if (depth == this.MaxDepth || currentState.getNumberOfAttackerCities() == currentState.getTeritoriesOwners().length() - 1 || currentState.getNumberOfAttackerCities() == 0) {
            currentState.setAttackerChar('1');
            return new StatePair(currentState, currentState.getGain());
        }
        State minState = null;
        int min_utility = Integer.MAX_VALUE;
        currentState.setAttackerChar('1');
        ArrayList<State>  children = currentState.getChildrensv2(); // possible steps that can done
        ArrayList<State> neighbors = new ArrayList<>();
        for(State st: children){
            ArrayList<State> attacks = st.getAttacks();
            for(State att: attacks){
                   neighbors.add(att);   
            } 
        }
        GamePlay.getInstance().currentPlayer().IncreaseExpansionStepsBy(neighbors.size());
        Collections.sort(neighbors);
        int idx = 0;
        for (State state : neighbors) {
            idx++;
            if (idx > 1) break;
            state.setAttackerChar('0');
            StatePair retStatePair = maximize(state, '0', depth + 1, alpha, beta);
            State retState = retStatePair.getFirst();
            int utility = retStatePair.getSecond();
            retState.setAttackerChar('1');
            if (min_utility > utility) {
                minState = retState;
                min_utility = utility;
            }
            if (min_utility <= alpha) break;
            if (min_utility < beta) beta = min_utility;
        }
        if (minState == null) minState = currentState;
        return new StatePair(minState, min_utility);
    }
    
}
