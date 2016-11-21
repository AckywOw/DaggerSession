package com.example.leet.e_shortestpath;

import java.util.Arrays;

/**
 * Created by AckywOw on 2016/6/14.
 */
public class Dijkstra {

  public static void main(String[] args) {
    int infinity = Integer.MAX_VALUE / 2;
    int[][] map = {
        { 0, 1, 12, infinity, infinity, infinity }, { infinity, 0, 9, 3, infinity, infinity },
        { infinity, infinity, 0, infinity, 5, infinity }, { infinity, infinity, 4, 0, 13, 15 },
        { infinity, infinity, infinity, infinity, 0, 4 },
        { infinity, infinity, infinity, infinity, infinity, 0 }
    };
    int[] distances = new int[6];
    for (int i = 0; i < map.length; i++) {
      distances[i] = map[0][i];
    }

    int[] book = new int[6];
    book[0] = 1;

    int min, u = 0;
    for (int i = 0; i < map.length - 1; i++) {
      min = infinity;
      for (int j = 0; j < map.length; j++) {
        if (book[j] == 0 && distances[j] < min) {
          min = distances[j];
          u = j;
        }
      }
      book[u] = 1;
      for (int k = 0; k < map.length; k++) {
        if (map[u][k] < infinity) {
          if (distances[k] > distances[u] + map[u][k]) {
            distances[k] = distances[u] + map[u][k];
          }
        }
      }
    }

    System.out.println(Arrays.toString(distances));
  }
}
