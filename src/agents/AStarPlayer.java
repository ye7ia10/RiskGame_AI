/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;


import gameMaps.Territory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import riskgame.GamePlay;
import riskgame.State;

/**
 *
 * @author mostafanabil198
 */
public class AStarPlayer extends Agent{
    
    
//    @Override
//    public void play() {
//        char playerChar = '1';
//        PriorityQueue<State> frontier = new PriorityQueue<>();
//        Set<String> inQueue = new HashSet<>();
//        Set<String> explored = new HashSet<>();
//        State currentState = GamePlay.getInstance().getGameMap().getCurrentState(playerChar, this);
//        currentState.setPlayerType(1);
//        inQueue.add(currentState.getTeritoriesOwners());
//        HashMap<String, State> stringToState = new HashMap<>();
//        stringToState.put(currentState.getTeritoriesOwners(), currentState);
//        /*ArrayList<State> children = currentState.getChildrens();
//        for(State st: children){
//            frontier.add(st);
//        }*/
//        frontier.add(currentState);
//        while(!frontier.isEmpty())
//        {
//            State newState = frontier.poll();
//            if(!stringToState.containsKey(newState.getTeritoriesOwners())) {
//                //System.err.println("BAAAAAAAAAAAAAAAAAAAAADDDDDDDDDDDDDDD");
//                System.err.println(newState.getTeritoriesOwners());
//            }
//            
//            
//            if (stringToState.containsKey(newState.getTeritoriesOwners()) && newState.getHeuristic() > stringToState.get(newState.getTeritoriesOwners()).getHeuristic() &&
//                    !(newState.equals(stringToState.get(newState.getTeritoriesOwners())) )) {
//               
//                continue;
//            }
//            // System.out.println("*****************************************************************************************");
//            inQueue.remove(newState.getTeritoriesOwners());
//            
//            
//            explored.add(newState.getTeritoriesOwners());
//            if(isReachedGoal(newState.getTeritoriesOwners())){
//                
//                State parent = null;
//                while(true) {
//                    parent = newState.getParent();
//                    if (parent == null) break;
//                    if (parent.getParent() == null) break;
//                    newState = parent;
//                }
//                
//                GamePlay.getInstance().getGameMap().setState(newState, playerChar, this);
//                //System.out.println("Finished A*");
//                return;
//              
//            }
//            
//            ArrayList<State> neighbors = newState.getChildrens();
//            
//            for(State neighbor : neighbors)
//            {
//                if (inQueue.contains(neighbor.getTeritoriesOwners())) {
//                    //System.err.println("Not happen firstly");
//                    if (stringToState.containsKey(neighbor.getTeritoriesOwners()) && stringToState.get(neighbor.getTeritoriesOwners()).getHeuristic() > neighbor.getHeuristic()) {
//                        stringToState.put(neighbor.getTeritoriesOwners(), neighbor);
//                        frontier.add(neighbor);
//                    }
//                    
//                }
//                              
//                else if (!explored.contains(neighbor.getTeritoriesOwners())){
//                    stringToState.put(neighbor.getTeritoriesOwners(), neighbor);
//                    frontier.add(neighbor);
//                    inQueue.add(neighbor.getTeritoriesOwners());
//                } 
//            }
//        }
////        Territory t = GamePlay.getInstance().getGameMap().getAgentTerritories(this).get(0);
////        t.setArmiesCount(t.getArmiesCount() + this.calculateAdditionalArmies());
////        Random rand = new Random();
////        GamePlay.getInstance().getGameMap().setState(currentState.getChildrens().get(rand.nextInt(currentState.getChildrens().size())), playerChar, this);
//        
//         ArrayList<State> neighbors = currentState.getChildrens();
//            
//        for(State neighbor : neighbors)
//        {
//            frontier.add(neighbor);
//        }
//        System.out.println("======RANDOOMMMm======");
//        GamePlay.getInstance().getGameMap().setState(frontier.poll(), playerChar, this);
//    }
//    private boolean isReachedGoal(String currentState) {
//        for (int i = 2; i < currentState.length(); i++) {
//            if (currentState.charAt(i) != currentState.charAt(1)) {
//                return false;
//            }
//        }
//        return true;
//    }
    
    @Override
    public void play(){
        System.out.println("A* Start");
        char playerChar = '1';
        PriorityQueue<State> frontier = new PriorityQueue<>();
        State currentState = GamePlay.getInstance().getGameMap().getCurrentState(playerChar, this);
        currentState.setPlayerType(1);
        //System.out.println(currentState.getNumberOfAttackerCities() + " size of attacks");
        ArrayList<State> children = currentState.getChildrensv2();
        for(State st: children){
            ArrayList<State> attacks = st.getAttacks();
            for(State att: attacks){
             if (attacks.size() > 1){
//               System.out.println(att.getNumberOfAttackerCities() + " size of attacker ");
             }
             frontier.add(att);   
            } 
        }
        State best = frontier.poll();
        if (best == null){
//            System.out.println("null stateeeeeeeeeee");
        }
        GamePlay.getInstance().getGameMap().setState(best, playerChar, this);
        System.out.println("A* End");
    }
    

    
}
