/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskgame;

import gameMaps.Territory;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author ahmed
 */
public class State implements Comparable<State>{
    private String teritoriesOwners;
    private ArrayList<Integer> armyCount = new ArrayList<>();
    private State parent;
    private int depth = 0;
    char attackerChar;
    int playerType; // 0 -> G, 1 -> A*, 2 -> Real A*
    int gain;
    Integer cashGainRealTime = null;
    public State(String teritoriesOwners, ArrayList<Integer> armyCount, char attackerChar) {
        this.teritoriesOwners = teritoriesOwners;
        this.armyCount = armyCount;
        this.attackerChar = attackerChar;
        gain = 0;
        playerType = -1;
    }
    
    public void setPlayerType(int type){
        this.playerType = type;
    }
    
    public char getAttackerChar() {
        return attackerChar;
    }

    public void setAttackerChar(char attackerChar) {
        this.attackerChar = attackerChar;
    }
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    
    public String getTeritoriesOwners() {
        return teritoriesOwners;
    }

    public void setTeritoriesOwners(String teritoriesOwners) {
        this.teritoriesOwners = teritoriesOwners;
    }

    public ArrayList<Integer> getArmyCount() {
        return armyCount;
    }

    public void setArmyCount(ArrayList<Integer> armyCount) {
        this.armyCount = armyCount;
    }

    public State getParent() {
        return parent;
    }

    
    public void setParent(State parent) {
        this.parent = parent;
    }
    
    public State clone() {
        String newteriorityOwner = this.teritoriesOwners;
        ArrayList<Integer> newArmyCount = (ArrayList<Integer>) this.armyCount.clone();
        State s = new State(newteriorityOwner, newArmyCount, attackerChar);
        s.setPlayerType(playerType);
        return s;
    }
    
    
    public ArrayList<State> getChildrens() {
        int bonus = getBonus();
        ArrayList<State>states = new ArrayList<>();
        char defenderChar = '0';
        if (attackerChar == '0') {
            defenderChar = '1';
        }
        int defenderBonus = 3, cnt = 0;
        for (int i = 1; i < teritoriesOwners.length(); i++) {
            if (teritoriesOwners.charAt(i) == defenderChar) {
                cnt++;
            }
        }
        cnt--;
        defenderBonus = Math.max(defenderBonus, cnt / 3);
        for (int i = 1; i < teritoriesOwners.length(); i++) {            
            if (teritoriesOwners.charAt(i) == attackerChar) {
                boolean has_enemy = false;
                int attackerID = i;
                ArrayList<Integer> neighbours = GamePlay.getInstance().getGameMap().getNeighbours(attackerID);
                boolean enter = false;
                for (int neb : neighbours) {
                    int maxi = -1, best = -1;
                    
                    if (teritoriesOwners.charAt(neb) == defenderChar) {
                        
                        for (int nebb : neighbours) {
                            if (teritoriesOwners.charAt(nebb) == defenderChar && neb != nebb) {
                                if (maxi < armyCount.get(nebb)) {
                                    maxi = armyCount.get(nebb);
                                    best = nebb;
                                }
                            }
                        }
                        
                        has_enemy = true;
                        Territory attacker = GamePlay.getInstance().getGameMap().getTerritory(attackerID);
                        Territory defender = GamePlay.getInstance().getGameMap().getTerritory(neb);
                        int needToLeft = 1;
                        if (best != -1) {
                            needToLeft = best + defenderBonus + 1;
                        }
                        
                        ArrayList<Integer> neighboursOfTheAttackedCity = GamePlay.getInstance().getGameMap().getNeighbours(neb);
                        maxi = -1;
                        for (int it : neighboursOfTheAttackedCity) {
                            if (teritoriesOwners.charAt(it) == defenderChar) {
                                if (armyCount.get(it) > maxi) {
                                    maxi = armyCount.get(it);
                                }
                                
                            }
                        }
                        int needToHave = 1;
                        if (maxi != -1) {
                            needToHave = maxi + defenderBonus;
                        }
                        for (int attackNum = attacker.getArmiesCount() + bonus - needToLeft;
                                    attackNum -  needToHave >= defender.getArmiesCount() + 1 && attackNum > 0 && !enter; attackNum--) {
                            State newState = this.clone();
                            newState.setAttack(attackerID, neb, bonus, attackNum);
                            newState.setParent(this);
                            newState.setDepth(getDepth() + 1);
                            states.add(newState);
                            enter = true;
                        }
                    }
                }
                if(has_enemy && !enter){
                    State newState = this.clone();
                    ArrayList<Integer> army = newState.getArmyCount();
                    army.set(attackerID, army.get(attackerID) + bonus);
                    newState.setArmyCount(army);
                    newState.setParent(this);
                    newState.setDepth(getDepth() + 1);
                    states.add(newState);
                }
            }
        }
        
        return states;
    }
    
     public ArrayList<State> getChildrensv2() {
        int expansionStep = 0;
        int bonus = getBonus();
        ArrayList<State>states = new ArrayList<>();
        char defenderChar = '0';
        if (attackerChar == '0') {
            defenderChar = '1';
        }
       ArrayList<Integer> inDanger = new ArrayList<>();
       ArrayList<Integer> onBorder = new ArrayList<>();
        for (int i = 1; i < teritoriesOwners.length(); i++) {
            boolean bord = false;
            if (teritoriesOwners.charAt(i) == attackerChar) {
            
                int  enemyArmy = 0;
                ArrayList<Integer> neighbours = GamePlay.getInstance().getGameMap().getNeighbours(i);
                for (int neb : neighbours) {
                    if (teritoriesOwners.charAt(neb) != attackerChar) {
                        bord = true;
                        enemyArmy = Math.max(armyCount.get(neb), enemyArmy);
                    } 
                }
                if (bord){
                    onBorder.add(i);
                }
                if (enemyArmy -1 > armyCount.get(i)){
                    inDanger.add(i);
                }
            }
         
        }
        if (inDanger.isEmpty()){
            inDanger.addAll(onBorder);
        }
        expansionStep = 5;
        State newst = this.clone();
        Set<String> visited = new HashSet<>();
        ArrayList<State> finalStates= new ArrayList<>();
        GamePlay.getInstance().currentPlayer().IncreaseExpansionStepsBy(expansionStep);
        AssignArmies(bonus,inDanger,newst,1,finalStates,visited);
        return finalStates;
     }
     
     
     
     public void AssignArmies(int bonus,ArrayList<Integer> inDanger,State st,int depth,ArrayList<State>finalStates,Set<String> visited) {
         
         //System.out.println(inDanger.size() + "   InDanger   ");
         //System.out.println(depth + "   depth   ");
//         System.out.println(bonus + "   bonus   ");
//         System.out.println("*********************");
         if (bonus  == 0 ){
//             System.out.println("It is bonus zero");
             State finalst = st.clone();
             finalStates.add(finalst);
             return;  
         }
         if (depth == 4){
//             System.out.println("It is depth four");
             for (int i =0;i<inDanger.size();i++){
                 State finalst = st.clone();
                 finalst.armyCount.set(inDanger.get(i), finalst.armyCount.get(inDanger.get(i))+bonus);
                 finalStates.add(finalst);
             }
             return;  
         }
         for(int i=0;i<inDanger.size();i++){
            State internalState = st.clone();
            internalState.armyCount.set(inDanger.get(i), internalState.armyCount.get(inDanger.get(i))+1);
            //if(!visited.contains(String.valueOf(internalState.armyCount))){
                 //visited.add(String.valueOf(internalState.armyCount));
                 AssignArmies(bonus-1,inDanger,internalState,depth+1,finalStates,visited);
            //}
         }
      
     }
     
     public ArrayList<State> getAttacks(){
        ArrayList<State> afterAttack = new ArrayList<>();
        char defenderChar = '0';
        if (attackerChar == '0') {
            defenderChar = '1';
        }
            afterAttack.add(this);
            for (int i = 1; i < teritoriesOwners.length(); i++) {
                if (teritoriesOwners.charAt(i) == attackerChar) {
                    // teriority => i + 1
                    int attackerID = i;
                    ArrayList<Integer> neighbours = GamePlay.getInstance().getGameMap().getNeighbours(attackerID);
                    for (int neb : neighbours) {
                        if (teritoriesOwners.charAt(neb) == defenderChar) {
                            // enemy
                            Territory attacker = GamePlay.getInstance().getGameMap().getTerritory(attackerID);
                            Territory defender = GamePlay.getInstance().getGameMap().getTerritory(neb);
                            for (int attackNum = attacker.getArmiesCount() - 1;
                                        attackNum >= defender.getArmiesCount() + 1; attackNum--) {
                                State newState = this.clone();
                                newState.setAttack(attackerID, neb, 0, attackNum);
                                newState.setParent(this);
                                newState.setDepth(getDepth() + 1);
                                afterAttack.add(newState);
                            }
                        }
                    }
                }
            }
            return afterAttack;
     }
     
     
    
    public void setAttack(int attackerID, int defenderID, int bonus, int attackNum) {
        Territory attacker = GamePlay.getInstance().getGameMap().getTerritory(attackerID);
        Territory defender = GamePlay.getInstance().getGameMap().getTerritory(defenderID);
        StringBuilder myBuilder = new StringBuilder(teritoriesOwners);
        myBuilder.setCharAt(defenderID, '1');
        teritoriesOwners = myBuilder.toString();
        armyCount.set(defenderID, attackNum - defender.getArmiesCount());
        armyCount.set(attackerID, attacker.getArmiesCount() - attackNum + bonus);
    }
    
    public int getHeuristicGreedy() {
        int enemyCnt = 0;
        for (int i = 1; i < teritoriesOwners.length(); i++) { 
            if (teritoriesOwners.charAt(i) != attackerChar) {
                enemyCnt++;
            }
        }
        int enemyBonus = Math.max(3, enemyCnt / 3);
        
        int g = depth, h = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        // calculate in danger
        
        for (int i = 1; i < teritoriesOwners.length(); i++) {
            
            if (teritoriesOwners.charAt(i) == attackerChar) {
                int friendArmy = 0, enemyArmy = 0;
                ArrayList<Integer> neighbours = GamePlay.getInstance().getGameMap().getNeighbours(i);
                for (int neb : neighbours) {
                    if (teritoriesOwners.charAt(neb) == attackerChar) {
                        friendArmy = Math.max(armyCount.get(neb), friendArmy);
                    } else {
                        enemyArmy = Math.max(armyCount.get(neb), enemyArmy);
                        set.add(i);
                    }
                }
                enemyArmy += enemyBonus;
                if (enemyArmy - 1 > armyCount.get(i)) {
//                    g += 1 + armyCount.get(i);
                      g+=2;
                    int maxret = enemyArmy - 1 - armyCount.get(i);
                    if (friendArmy - 1 > maxret) {
                        g--;
                    }
                }
                
            } else {
                h++;
            }
        }
//        if (print)
//            System.out.println("total:"+ (g+h)+" g:" +g +" h:"+h +" current state: "+ teritoriesOwners+" cities owned: "+getNumberOfAttackerCities()+" number of soldiers: "+armyCount);
        return  h;
    }
    
    
    
    public int getHeuristic() {
        return getGain();
    }
    
    public int getOldGain(){
        return this.gain;
    }
    
    
    public int getGain() {  
        
        int enemyCnt = 0;
        for (int i = 1; i < teritoriesOwners.length(); i++) { 
            if (teritoriesOwners.charAt(i) != attackerChar) {
                enemyCnt++;
            }
        }
        int enemyBonus = Math.max(3, enemyCnt / 3);
        
        // calculate in danger
        gain = 0;
        // number of territories i have ---- 15
        gain += 15 * this.getNumberOfAttackerCities();
        // number of safe territories ----- 10
        int safeTerritories = 0;
        int inDangerTerritories = 0;
        for (int i = 1; i < teritoriesOwners.length(); i++) {
            
            if (teritoriesOwners.charAt(i) == attackerChar) {
                gain += 2 * Math.log(armyCount.get(i));
                boolean has_enemy = false;
                int friendArmy = 0, enemyArmy = 0;
                ArrayList<Integer> neighbours = GamePlay.getInstance().getGameMap().getNeighbours(i);
                for (int neb : neighbours) {
                    if (teritoriesOwners.charAt(neb) == attackerChar) {
                        friendArmy = Math.max(armyCount.get(neb), friendArmy);
                    } else {
                        enemyArmy = Math.max(armyCount.get(neb), enemyArmy);
                        has_enemy = true;
                    }
                }
                enemyArmy += enemyBonus;
                if(!has_enemy){
                    safeTerritories++;
                } else if(enemyArmy - 1 > armyCount.get(i)) {
                    inDangerTerritories++;
                    int maxret = enemyArmy - 1 - armyCount.get(i);
                } else {
                    safeTerritories++;
                }
                if (has_enemy){
                    //gain += 1 * (armyCount.get(i) - enemyArmy);
                }
            }
        }
        gain += 10 * safeTerritories;
        // number of armies in borders ------ 1

        // In danger "7d ya5odha" -5
        gain -= 5 * inDangerTerritories;        
        
        
        return gain;
    }
    public int getGainMinMax() {  
        
        gain = 0;
        int enemyCnt = 0;
        for (int i = 1; i < teritoriesOwners.length(); i++) { 
            if (teritoriesOwners.charAt(i) != attackerChar) {
                enemyCnt++;
            }
        }
        int enemyBonus = Math.max(3, enemyCnt / 3);
        
        // calculate in danger
        
        // number of territories i have ---- 5
        gain += 5 * this.getNumberOfAttackerCities();
        // number of safe territories ----- 10
        int safeTerritories = 0;
        int inDangerTerritories = 0;
        for (int i = 1; i < teritoriesOwners.length(); i++) {
            
            if (teritoriesOwners.charAt(i) == attackerChar) {
                boolean has_enemy = false;
                int friendArmy = 0, enemyArmy = 0;
                ArrayList<Integer> neighbours = GamePlay.getInstance().getGameMap().getNeighbours(i);
                for (int neb : neighbours) {
                    if (teritoriesOwners.charAt(neb) == attackerChar) {
                        friendArmy = Math.max(armyCount.get(neb), friendArmy);
                    } else {
                        enemyArmy = Math.max(armyCount.get(neb), enemyArmy);
                        has_enemy = true;
                    }
                }
                enemyArmy += enemyBonus;
                if(!has_enemy){
                    safeTerritories++;
                } else if(enemyArmy - 1 > armyCount.get(i)) {
                    inDangerTerritories++;
                    int maxret = enemyArmy - 1 - armyCount.get(i);
                } else {
                    safeTerritories++;
                }
                if (has_enemy){
                    gain += 1 * (armyCount.get(i) - enemyArmy);
                }
            }
        }
        gain += 10 * safeTerritories;
        // number of armies in borders ------ 1
        
        // al territory ali 7gm beha w ali 7hgm 3leha... a7ot fl a3tbar 7ib2o safe wla 7ita5do mni tani .. lw safe yb2a point lw 7tta5d nn2s point
        
        return gain;
    }
    private boolean isSafe(int i) {
        ArrayList<Integer> neighbours = GamePlay.getInstance().getGameMap().getNeighbours(i);
        for (int neb: neighbours) {
            if (teritoriesOwners.charAt(i) != teritoriesOwners.charAt(neb) && armyCount.get(i) < armyCount.get(neb) - 1) {
                return false;
            }
        }
        return false;
    }
    public int getGainRealtime() {
        if (this.getParent() == null) {
            return 0;
        }
        int fun = 0;

        String parentTer = getParent().getTeritoriesOwners();
        for (int i = 1; i < teritoriesOwners.length(); i++) {
            
            if (teritoriesOwners.charAt(i) == parentTer.charAt(i)) {
                if (isSafe(i) && !getParent().isSafe(i)) {
                    fun += 10;
                } else if (!isSafe(i) && getParent().isSafe(i)) {
                    fun -= 10;
                }
            } else {
                if(teritoriesOwners.charAt(i) == attackerChar) {
                    fun += 15;
                } else {
                    fun -= 15;
                }
            }
        }
        
        return cashGainRealTime = fun;
    }
    
    public int getNumberOfAttackerCities() {
        int num = 0;
        for (int i = 0; i < teritoriesOwners.length(); i++) {
            if (teritoriesOwners.charAt(i) == attackerChar) {
                num++;
            }
        }
        return num;
    }
    
    private int getBonus() {
        int myTerCount = 0;
        for (int i = 1; i < teritoriesOwners.length(); i++) {
            if (teritoriesOwners.charAt(i) == attackerChar) {
                myTerCount++;
            }
        }
        return Math.max(3, myTerCount / 3);
        
    }
    
    @Override
    public int compareTo(State t) {
        
        if(playerType == 0){
            return Integer.compare(getHeuristicGreedy(), t.getHeuristicGreedy());
        } else if (playerType == 1){
            return Integer.compare(t.getGain(), getGain());
        } else {
            
            return Integer.compare(t.getGainRealtime(), getGainRealtime());
        }
    }
    
}
