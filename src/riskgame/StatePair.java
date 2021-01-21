/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskgame;

/**
 *
 * @author mostafanabil198
 */
public class StatePair {
    private State first;
    private int second;

    public State getFirst() {
        return first;
    }

    public void setFirst(State first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
    public StatePair(State first, int second){
        this.first = first;
        this.second = second;
    }
    
    
}
