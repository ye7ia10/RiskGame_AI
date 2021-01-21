/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameMaps;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author mostafanabil198
 */
public class EgyptMap extends Map{
    public EgyptMap(){
        for(int i = 0; i <= 27; i++){
            Territory t = new Territory(i);
            territories.add(t);
        }
        neighbours.put(1, new ArrayList<>(Arrays.asList(2,3,14,21)));
        neighbours.put(2, new ArrayList<>(Arrays.asList(1,3)));
        neighbours.put(3, new ArrayList<>(Arrays.asList(2,4,9,10,14)));
        neighbours.put(4, new ArrayList<>(Arrays.asList(3,5,9)));
        neighbours.put(5, new ArrayList<>(Arrays.asList(12,4,6,9,11,10)));
        neighbours.put(6, new ArrayList<>(Arrays.asList(5)));
        neighbours.put(7, new ArrayList<>(Arrays.asList(13,8)));
        neighbours.put(8, new ArrayList<>(Arrays.asList(7,13,17,18)));
        neighbours.put(9, new ArrayList<>(Arrays.asList(4,3,5,10)));
        neighbours.put(10, new ArrayList<>(Arrays.asList(9,3,11,5,14)));
        neighbours.put(11, new ArrayList<>(Arrays.asList(10,12,16,14,5)));
        neighbours.put(12, new ArrayList<>(Arrays.asList(5,13,11,16,17)));
        neighbours.put(13, new ArrayList<>(Arrays.asList(12,7,8,17)));
        neighbours.put(14, new ArrayList<>(Arrays.asList(1,20,19,15,11,10,21,16,3)));
        neighbours.put(15, new ArrayList<>(Arrays.asList(14,19)));
        neighbours.put(16, new ArrayList<>(Arrays.asList(14,11,17,19,12,23)));
        neighbours.put(17, new ArrayList<>(Arrays.asList(16,13,23,12,8,18)));
        neighbours.put(18, new ArrayList<>(Arrays.asList(17,8)));
        neighbours.put(19, new ArrayList<>(Arrays.asList(14,15,16,20,23)));
        neighbours.put(20, new ArrayList<>(Arrays.asList(14,19,22,21,23)));
        neighbours.put(21, new ArrayList<>(Arrays.asList(1,14,20,22,24,25,26,27)));
        neighbours.put(22, new ArrayList<>(Arrays.asList(20,21,23,24)));
        neighbours.put(23, new ArrayList<>(Arrays.asList(17,16,19,20,22,24,25,26,27)));
        neighbours.put(24, new ArrayList<>(Arrays.asList(21,22,23,25)));
        neighbours.put(25, new ArrayList<>(Arrays.asList(21,24,23,26)));
        neighbours.put(26, new ArrayList<>(Arrays.asList(21,25,23,27)));
        neighbours.put(27, new ArrayList<>(Arrays.asList(21,26,23)));
    }
}
