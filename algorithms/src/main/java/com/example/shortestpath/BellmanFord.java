package com.example.shortestpath;

import java.util.Arrays;

/**
 * Created by AckywOw on 2016/6/20.
 */
public class BellmanFord {

    public static void main(String[] args) {
        int[][] map = {
                {1, 2, 2},
                {0, 1, -3},
                {0, 4, 5},
                {3, 4, 2},
                {2, 3, 3}
        };
        int[] distances = new int[5], u = new int[5], v = new int[5], w = new int[5];
        int infinity = Integer.MAX_VALUE / 2;
        int n = 5, m = 5;
        for (int i = 0; i < m; i++) {
            u[i] = map[i][0];
            v[i] = map[i][1];
            w[i] = map[i][2];
        }
        for (int i = 0; i < n; i++) {
            distances[i] = infinity;
        }
        distances[0] = 0;

        boolean check; //优化，减少判断松弛次数
        for (int k = 0; k < n - 1; k++) {
            check = false;
            for (int i = 0; i < m; i++) {
                if (distances[v[i]] > distances[u[i]] + w[i]) {
                    distances[v[i]] = distances[u[i]] + w[i];
                    check = true;
                }
            }
            if (!check)
                break;
        }

        //检测负权回路
        boolean flag = false; 
        for (int i = 0; i < m; i++) {
            if (distances[v[i]] > distances[u[i]] + w[i]) {
                distances[v[i]] = distances[u[i]] + w[i];
                flag = true;
            }
        }
        if (flag) {
            System.out.println("111");
        }

        System.out.println(Arrays.toString(distances));
    }
}
