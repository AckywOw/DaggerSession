package com.example.leet.e_shortestpath;

import com.example.util.Utils;

/**
 * Created by AckywOw on 2016/6/14.
 */
public class FloydTest {
  public static void main(String[] args) {
    int infinity = Integer.MAX_VALUE / 2;
    int[][] map = {
        { 0, 2, 6, 4 }, { infinity, 0, 3, infinity }, { 7, infinity, 0, 1 }, { 5, infinity, 12, 0 }
    };
    Utils.printArrs(map);
    //// TODO: 2016/6/29
    for (int i = 0; i < map.length; i++) {
      for (int k = 0; k < map.length; k++) {
        for (int u = 0; u < map.length; u++) {
          if (map[k][u] > map[k][i] + map[i][u]) {
            map[k][u] = map[k][i] + map[i][u];
          }
        }
      }
    }
    System.out.println("---------------------");
    Utils.printArrs(map);
  }
}
