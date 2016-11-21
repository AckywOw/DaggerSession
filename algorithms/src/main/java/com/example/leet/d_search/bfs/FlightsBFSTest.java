package com.example.leet.d_search.bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by AckywOw on 2016/6/14.
 */
public class FlightsBFSTest {
  public static void main(String[] args) {
    int[][] map = {
        { 0, 1, 1, 0, 0 }, { 1, 0, 1, 1, 0 }, { 1, 1, 0, 1, 1 }, { 0, 1, 1, 0, 1 },
        { 0, 0, 1, 1, 0 }
    };
    int[] book = new int[5];
    book[0] = 1;
    int sum = 5, start = 0, end = 4, head = 0, tail = 0;

    List<Step> steps = new ArrayList<>();
    steps.add(new Step(0, 0, null));
    tail++;
    while (head < tail) {
      for (int i = 0; i < sum; i++) {
        if (map[head][i] > 0 && book[i] == 0) {
          start = i;
          steps.add(new Step(i, steps.get(head).sum + 1, steps.get(head)));
          book[i] = 1;
          tail++;
        }
        if (start == end) {
          break;
        }
      }
      if (start == end) {
        break;
      }
      head++;
    }

    ArrayList<Step> finalSteps = new ArrayList<>();
    makeSteps(steps.get(tail - 1), finalSteps);
    System.out.println("to 5:" + finalSteps.toString());
  }

  private static void makeSteps(Step last, ArrayList<Step> finalSteps) {
    finalSteps.add(last);
    if (last.front != null) {
      makeSteps(last.front, finalSteps);
    } else {
      Collections.reverse(finalSteps);
    }
  }

  static class Step {
    int num;
    int sum;
    Step front;

    public Step(int num, int sum, Step front) {
      this.num = num;
      this.sum = sum;
      this.front = front;
    }

    @Override
    public String toString() {
      return "[" + num + ", " + sum + "]";
    }
  }
}
