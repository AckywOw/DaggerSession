package com.example.leet.d_search.dfs;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by AckywOw on 2016/6/14.
 */
public class CitysDFSTest {

  public static void main(String[] args) {
    int[][] map = {
        { 0, 2, -1, -1, 10 }, { -1, 0, 3, -1, 7 }, { 4, -1, 0, 4, -1 }, { -1, -1, -1, 0, 5 },
        { -1, -1, 3, -1, 0 }
    };
    int cityNum = 5;
    int[][] book = new int[5][5];
    int start = 0, end = 4, distance = 0;
    AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
    LinkedList<Integer> path = new LinkedList<>();
    path.add(0);
    book[0][0] = 1;
    dfs(start, end, distance, min, cityNum, map, book, path);
  }

  private static void dfs(int start, int end, int distance, AtomicInteger min, int cityNum,
      int[][] map, int[][] book, LinkedList<Integer> path) {
    if (distance > min.get()) {
      return;
    }
    if (start == end) {
      min.set(distance);
      System.out.println("to the end:" + path.toString());
      System.out.println("distance:" + distance);
      System.out.println("-------------------------------");
      return;
    }

    for (int i = 1; i < cityNum; i++) {
      if (map[start][i] > 0 && book[start][i] == 0) {
        book[start][i] = 1;
        path.addLast(i);
        distance += map[start][i];
        dfs(i, end, distance, min, cityNum, map, book, path);
        path.removeLast();
        book[start][i] = 0;
        distance -= map[start][i];
      }
    }
  }
}
