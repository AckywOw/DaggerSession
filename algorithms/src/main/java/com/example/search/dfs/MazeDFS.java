package com.example.search.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by AckywOw on 2016/6/12.
 */
public class MazeDFS {

    static class Step {
        int x, y;

        public Step(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }
    }


    public static void main(String[] args) {
        int startX = 0, startY = 0, endX = 3, endY = 2;
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
        ArrayList<Step> steps = new ArrayList<>();
        ArrayList<Step> finalSteps = new ArrayList<>();
        steps.add(new Step(0, 0));

        AtomicInteger sum = new AtomicInteger(Integer.MAX_VALUE);
        dfsMaze(startX, startY, endX, endY, width, height, maze, book, next, 0, steps, sum, finalSteps);
        System.out.println("sum:" + sum.toString());
        System.out.println("step:" + finalSteps.toString());
    }

    private static void dfsMaze(int x, int y, int endX, int endY, int width, int height, int[][] maze, int[][] book,
                                int[][] next, int step, List<Step> steps, AtomicInteger min, ArrayList<Step>
                                        finalSteps) {
        if (x == endX && y == endY) {
            if (min.get() > step) {
                min.set(step);
                finalSteps.clear();
                finalSteps.addAll(steps);
            }
            return;
        }
        int tx, ty;
        for (int i = 0; i < next.length; i++) {
            tx = next[i][0] + x;
            ty = next[i][1] + y;
            if (tx < 0 || tx >= height || ty < 0 || ty >= width) {
                continue;
            }

            if (maze[tx][ty] == 0 && book[tx][ty] == 0) {
                book[tx][ty] = 1;
                steps.add(step + 1, new Step(tx, ty));
                steps = steps.subList(0, step + 2);
                dfsMaze(tx, ty, endX, endY, width, height, maze, book, next, step + 1, steps, min, finalSteps);
                book[tx][ty] = 0;
            }
        }
        return;
    }
}
