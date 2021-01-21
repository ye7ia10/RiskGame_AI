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
public class USMap extends Map{
    public USMap(){
        for(int i = 0; i <= 50; i++){
            Territory t = new Territory(i);
            territories.add(t);
        }
        neighbours.put(1, new ArrayList<>(Arrays.asList(2, 5, 50)));
        neighbours.put(2, new ArrayList<>(Arrays.asList(1, 3, 4, 5)));
        neighbours.put(3, new ArrayList<>(Arrays.asList(2, 4, 9, 49)));
        neighbours.put(4, new ArrayList<>(Arrays.asList(2, 3, 5, 8, 9)));
        neighbours.put(5, new ArrayList<>(Arrays.asList(1, 2, 4, 6, 7, 8)));
        neighbours.put(6, new ArrayList<>(Arrays.asList(5, 7, 16, 17)));
        neighbours.put(7, new ArrayList<>(Arrays.asList(5, 6, 8, 10, 15, 16)));
        neighbours.put(8, new ArrayList<>(Arrays.asList(4, 5, 7, 9,10,11)));
        neighbours.put(9, new ArrayList<>(Arrays.asList(3, 4, 8, 10, 11, 49)));
        neighbours.put(10, new ArrayList<>(Arrays.asList(7, 8, 9, 11, 13, 14, 15)));
        neighbours.put(11, new ArrayList<>(Arrays.asList(8, 9, 10, 12, 13)));
        neighbours.put(12, new ArrayList<>(Arrays.asList(11, 13, 21, 22)));
        neighbours.put(13, new ArrayList<>(Arrays.asList(10, 11, 12, 14, 20, 21)));
        neighbours.put(14, new ArrayList<>(Arrays.asList(10, 13, 15, 20)));
        neighbours.put(15, new ArrayList<>(Arrays.asList(7, 10, 14, 16, 19, 20)));
        neighbours.put(16, new ArrayList<>(Arrays.asList(6, 7, 15, 17, 18, 19)));
        neighbours.put(17, new ArrayList<>(Arrays.asList(6, 16, 18)));
        neighbours.put(18, new ArrayList<>(Arrays.asList(16, 17, 19, 34)));
        neighbours.put(19, new ArrayList<>(Arrays.asList(15, 16, 18, 20, 33, 34)));
        neighbours.put(20, new ArrayList<>(Arrays.asList(13, 14, 15, 19, 21, 31, 32, 33)));
        neighbours.put(21, new ArrayList<>(Arrays.asList(12, 13, 20, 22, 23, 31)));
        neighbours.put(22, new ArrayList<>(Arrays.asList(12, 21, 23)));
        neighbours.put(23, new ArrayList<>(Arrays.asList(21, 22, 24, 31)));
        neighbours.put(24, new ArrayList<>(Arrays.asList(23, 25, 26, 31)));
        neighbours.put(25, new ArrayList<>(Arrays.asList(24, 26)));
        neighbours.put(26, new ArrayList<>(Arrays.asList(24, 25, 27, 28, 31)));
        neighbours.put(27, new ArrayList<>(Arrays.asList(26, 28)));
        neighbours.put(28, new ArrayList<>(Arrays.asList(26, 27, 29, 31)));
        neighbours.put(29, new ArrayList<>(Arrays.asList(28, 30, 31, 32, 48)));
        neighbours.put(30, new ArrayList<>(Arrays.asList(29, 32, 37, 38, 48)));
        neighbours.put(31, new ArrayList<>(Arrays.asList(20, 21, 23, 24, 26, 28, 29, 32)));
        neighbours.put(32, new ArrayList<>(Arrays.asList(20, 29, 30, 31, 33, 35, 37)));
        neighbours.put(33, new ArrayList<>(Arrays.asList(19, 20, 32, 34, 35, 36)));
        neighbours.put(34, new ArrayList<>(Arrays.asList(18, 19, 33, 36)));
        neighbours.put(35, new ArrayList<>(Arrays.asList(32, 33, 36, 37)));
        neighbours.put(36, new ArrayList<>(Arrays.asList(33, 34, 35, 37)));
        neighbours.put(37, new ArrayList<>(Arrays.asList(30, 32, 35, 36, 38)));
        neighbours.put(38, new ArrayList<>(Arrays.asList(30, 37, 39, 46, 47, 48)));
        neighbours.put(39, new ArrayList<>(Arrays.asList(38, 40, 43, 45, 46)));
        neighbours.put(40, new ArrayList<>(Arrays.asList(39, 41, 43)));
        neighbours.put(41, new ArrayList<>(Arrays.asList(40, 42, 43)));
        neighbours.put(42, new ArrayList<>(Arrays.asList(41)));
        neighbours.put(43, new ArrayList<>(Arrays.asList(39, 40, 41, 44, 45)));
        neighbours.put(44, new ArrayList<>(Arrays.asList(43, 45)));
        neighbours.put(45, new ArrayList<>(Arrays.asList(39, 43, 44, 46)));
        neighbours.put(46, new ArrayList<>(Arrays.asList(38, 39, 45, 47)));
        neighbours.put(47, new ArrayList<>(Arrays.asList(38, 46, 48)));
        neighbours.put(48, new ArrayList<>(Arrays.asList(29, 30, 38, 47)));
        neighbours.put(49, new ArrayList<>(Arrays.asList(3, 9)));
        neighbours.put(50, new ArrayList<>(Arrays.asList(1)));
    }
}
