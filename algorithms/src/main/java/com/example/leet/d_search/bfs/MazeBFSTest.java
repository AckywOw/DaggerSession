package com.example.leet.d_search.bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 迷宫找人
 * Created by AckywOw on 2016/6/12.
 */
public class MazeBFSTest {

    static class Step {
        int x, y, step;
        Step f;

        public Step(int x, int y, Step father, int step) {
            this.x = x;
            this.y = y;
            this.f = father;
            this.step = step;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }
    }

    public static void main(String[] args) {
        int startX = 0, startY = 0, endX = 4, endY = 1;
        int width = 4, height = 7;
        int[][] maze = {
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0}};

        int[][] book = new int[height][width];
        int[][] next = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        List<Step> steps = new ArrayList<>();
        ArrayList<Step> finalSteps = new ArrayList<>();

        int head = 0, tail = 0, ty, tx;
        steps.add(new Step(0, 0, null, 0));
        tail++;
        boolean getIt = false;
        while (head < tail) {
            for (int i = 0; i < next.length; i++) {
                tx = steps.get(head).x + next[i][0];
                ty = steps.get(head).y + next[i][1];
                if (tx < 0 || tx >= height || ty < 0 || ty >= width) {
                    continue;
                }
                if (maze[tx][ty] > 0) {
                    continue;
                }
                if (book[tx][ty] == 0) {
                    book[tx][ty] = 1;
                    steps.add(new Step(tx, ty, steps.get(head), steps.get(head).step + 1));
                    tail++;
                }
                if (tx == endX && ty == endY) {
                    getIt = true;
                    break;
                }
            }
            if (getIt) {
                break;
            }
            head++;
        }

        Step last = steps.get(tail - 1);
        makeSteps(last, finalSteps);
        System.out.println("sum:" + last.step);
        System.out.println("step:" + finalSteps.toString());
    }

    private static void makeSteps(Step last, ArrayList<Step> finalSteps) {
        finalSteps.add(last);
        if (last.f != null) {
            makeSteps(last.f, finalSteps);
        } else {
            Collections.reverse(finalSteps);
        }
    }
}
