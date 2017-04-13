package com.example.leet.d_search.bfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 最大岛面积
 * Created by AckywOw on 2016/6/13.
 */
public class IslandBFS {
  public static void main(String[] args) {
    int[][] next = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    int[][] map = {
        { 1, 2, 1, 0, 0, 0, 0, 0, 2, 3 },
        { 3, 0, 2, 0, 1, 2, 1, 0, 1, 2 },
        { 4, 0, 1, 0, 1, 2, 3, 2, 0, 1 },
        { 3, 2, 0, 0, 0, 1, 2, 4, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 1, 5, 3, 0 },
        { 0, 1, 2, 1, 0, 1, 5, 4, 3, 0 },
        { 0, 1, 2, 3, 1, 3, 6, 2, 1, 0 },
        { 0, 0, 3, 4, 8, 9, 7, 5, 0, 0 },
        { 0, 0, 0, 3, 7, 8, 6, 0, 1, 2 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }
    };
    int startX = 5, startY = 7, width = 10, height = 10;
    List<Step> steps = new ArrayList<Step>();
    steps.add(new Step(startX, startY));
    int head = 0, tail = 1, tx = 0, ty = 0;
    while (head < tail) {//利用队列的性质来进行探索
      for (int i = 0; i < next.length; i++) { //for循环，分别向4个方向前进一步寻找新点
        tx = steps.get(head).x + next[i][0];
        ty = steps.get(head).y + next[i][1];
        if (tx < 0 || tx >= width || ty < 0 || ty >= height) {//判断是否出界
          continue;
        }
        Step newStep = new Step(tx, ty);
        if (map[tx][ty] > 0 && !steps.contains(newStep)) {//保证这个点是有效的，并且是新点
          steps.add(newStep);
          tail++;// 增加一个新点
        }
      }
      head++;//开始下一个点的寻找
    }

    System.out.println("sum:" + steps.size());
    System.out.println("content:" + steps.toString());
  }

  static class Step {
    int x, y;

    public Step(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Step step = (Step) o;

      if (x != step.x) return false;
      return y == step.y;
    }

    @Override
    public int hashCode() {
      int result = x;
      result = 31 * result + y;
      return result;
    }

    @Override
    public String toString() {
      return "[" + x + ", " + y + "]";
    }
  }
}
