package com.example.shortestpath;

import com.example.util.Utils;

/**
 * Created by AckywOw on 2016/6/14.
 */
public class Floyd {
    public static void main(String[] args) {
        int infinity = Integer.MAX_VALUE/2;
        int[][] map = {
                {0, 2, 6, 4},
                {infinity, 0, 3, infinity},
                {7, infinity, 0, 1},
                {5, infinity, 12, 0}};
        Utils.printArrs(map);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                for (int k = 0; k < map.length; k++) {
                    if (map[j][k] > map[j][i] + map[i][k])
                        map[j][k] = map[j][i] + map[i][k];
                }
            }
        }
        System.out.println("---------------------");
        Utils.printArrs(map);
    }

}
