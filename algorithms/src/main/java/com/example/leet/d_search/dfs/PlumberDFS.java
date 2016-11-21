package com.example.leet.d_search.dfs;

/**
 * 1:└
 * 2:┌
 * 3:┐
 * 4:┘
 * 5:─
 * 6:│
 * Created by AckywOw on 2016/6/13.
 */
public class PlumberDFS {
  public static void main(String[] args) {
    int[][] book = new int[50][40];
    int[][] map = {
        { 5, 3, 5, 3 }, { 1, 5, 3, 0 }, { 2, 3, 5, 1 }, { 6, 1, 1, 5 }, { 1, 5, 5, 4 }
    };

    int startX = 0, startY = 0, endX = 4, endY = 3;
    dfs(startX, startY, endX, endY, map, book, From.LEFT);
  }

  private static void dfs(int startX, int startY, int endX, int endY, int[][] map, int[][] book,
      From front) {
    if (startX == endX && startY == endY + 1) {
      System.out.println("getIt!");
      return;
    }
    if (startX < 0 || startX > endX || startY < 0 || startY > endY || book[startX][startY] == 1) {
      return;
    }
    book[startX][startY] = 1;
    if (map[startX][startY] >= 5 && map[startX][startY] <= 6) {
      if (front == From.LEFT) {
        dfs(startX, startY + 1, endX, endY, map, book, From.LEFT);
      } else if (front == From.UP) {
        dfs(startX + 1, startY, endX, endY, map, book, From.UP);
      } else if (front == From.RIGHT) {
        dfs(startX, startY - 1, endX, endY, map, book, From.RIGHT);
      } else if (front == From.DOWN) {
        dfs(startX - 1, startY, endX, endY, map, book, From.DOWN);
      }
    } else if (map[startX][startY] >= 1 && map[startX][startY] <= 4) {
      if (front == From.LEFT) {
        dfs(startX + 1, startY, endX, endY, map, book, From.UP);
        dfs(startX - 1, startY, endX, endY, map, book, From.DOWN);
      } else if (front == From.UP) {
        dfs(startX, startY + 1, endX, endY, map, book, From.LEFT);
        dfs(startX, startY - 1, endX, endY, map, book, From.RIGHT);
      } else if (front == From.RIGHT) {
        dfs(startX + 1, startY, endX, endY, map, book, From.UP);
        dfs(startX - 1, startY, endX, endY, map, book, From.DOWN);
      } else if (front == From.DOWN) {
        dfs(startX, startY + 1, endX, endY, map, book, From.LEFT);
        dfs(startX, startY - 1, endX, endY, map, book, From.RIGHT);
      }
    }
    book[startX][startY] = 0;
  }

  enum From {LEFT, UP, RIGHT, DOWN}
}
