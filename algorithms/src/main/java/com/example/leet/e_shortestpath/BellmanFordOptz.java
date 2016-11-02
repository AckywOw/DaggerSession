package com.example.leet.e_shortestpath;

import java.util.Arrays;

/**
 * Created by AckywOw on 2016/6/20.
 */
public class BellmanFordOptz {

    public static void main(String[] args) {
        int[][] map = {
                {0, 1, 2},
                {0, 4, 10},
                {1, 2, 3},
                {1, 4, 7},
                {2, 3, 4},
                {3, 4, 5},
                {4, 2, 6}
        };
        int n = 5, m = 7;
        int[] distances = new int[n], u = new int[m], v = new int[m], w = new int[m],
                first = new int[n + 1], next = new int[m + 1], book = new int[n];

        int infinity = Integer.MAX_VALUE / 2;
        for (int i = 0; i < n; i++) {
            distances[i] = infinity;
            book[0] = 0;
            first[i] = -1;
        }
        distances[0] = 0;

        for (int i = 0; i < m; i++) {
            u[i] = map[i][0];
            v[i] = map[i][1];
            w[i] = map[i][2];
            next[i] = first[u[i]];
            first[u[i]] = i;
        }

        int[] que = new int[1000];
        int head = 0, tail = 0, k;
        que[head] = 0;
        book[head] = 1;
        tail++;

        while (head < tail) {
            k = first[que[head]];
            while (k != -1) {
                if (distances[v[k]] > distances[u[k]] + w[k]) {
                    distances[v[k]] = distances[u[k]] + w[k];
                    if (book[v[k]] == 0) {
                        //入队
                        que[tail] = k;
                        tail++;
                        book[v[k]] = 1;
                    }
                }
                k = next[k];
            }

            //出队
            book[que[head]] = 0;
            head++;
        }

        System.out.println(Arrays.toString(distances));
    }
}
