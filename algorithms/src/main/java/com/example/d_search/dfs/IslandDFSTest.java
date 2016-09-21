package com.example.d_search.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 最大岛面积
 * Created by AckywOw on 2016/6/13.
 */
public class IslandDFSTest {
    static class Step {
        int x, y;

        public Step(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Step step = (Step) o;

            if (x != step.x)
                return false;
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

    public static void main(String[] args) {
        int startX = 5, startY = 7, width = 10, height = 10;
        int[][] next = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[][] map = {
                {1, 2, 1, 0, 0, 0, 0, 0, 2, 3},
                {3, 0, 2, 0, 1, 2, 1, 0, 1, 2},
                {4, 0, 1, 0, 1, 2, 3, 2, 0, 1},
                {3, 2, 0, 0, 0, 1, 2, 4, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 5, 3, 0},
                {0, 1, 2, 1, 0, 1, 5, 4, 3, 0},
                {0, 1, 2, 3, 1, 3, 6, 2, 1, 0},
                {0, 0, 3, 4, 8, 9, 7, 5, 0, 0},
                {0, 0, 0, 3, 7, 8, 6, 0, 1, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}};
        int[][] book = new int[width][height];
        List<Step> finalSteps = new ArrayList<Step>();
        finalSteps.add(new Step(startX, startY));

        book[startX][startY] = 1;
        letsGo(startX, startY, finalSteps, map, next, book, width, height);
        System.out.println("sum:" + finalSteps.size());
        System.out.println("content:" + finalSteps.toString());
    }

    private static void letsGo(int x, int y, List<Step> finalSteps, int[][]
            map, int[][] next, int[][] book, int width, int height) {
        for (int i = 0; i < next.length; i++) {
            int tx = x + next[i][0];
            int ty = y + next[i][1];
            if (tx < 0 || tx >= height || ty < 0 || ty >= width) {
                continue;
            }

            if (map[tx][ty] > 0 && book[tx][ty] == 0) {
                book[tx][ty] = 1;
                finalSteps.add(new Step(tx, ty));
                letsGo(tx, ty, finalSteps, map, next, book, width, height);
            }
        }
    }
}
