package com.example.more;

import com.example.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by AckywOw on 2016/6/22.
 */
public class Kruskal {
    static class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return  String.valueOf(w);
        }
    }

    public static void main(String[] args) {
        int n = 6, m = 9;
        int[][] map = {
                {2, 4, 11},
                {3, 5, 13},
                {4, 6, 3},
                {5, 6, 4},
                {2, 3, 6},
                {4, 5, 7},
                {1, 2, 1},
                {3, 4, 9},
                {1, 3, 2}
        };
        List<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < map.length; i++) {
            edges.add(new Edge(map[i][0], map[i][1], map[i][2]));
        }
        quickSort(edges);
        System.out.println(edges.toString());

        int[] nums = new int[n + 1];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = i;
        }

        int count = 0, sum = 0;

        for (int i = 0; i < edges.size(); i++) {
            if (merge(nums, edges.get(i).u, edges.get(i).v)) {
                count++;
                sum += edges.get(i).w;
            }
            if (count == n - 1) {
                break;
            }
        }
        System.out.println(sum);
    }

    private static boolean merge(int[] nums, int u, int v) {
        int t1 = getFather(nums, u);
        int t2 = getFather(nums, v);
        if (t2 != t1) { //说明是新的需要合并的 连接的
            nums[t2] = t1;
            getFather(nums, v);
            return true;
        }
        return false;
    }

    private static int getFather(int[] nums, int u) {
        if (nums[u] != u) {
            nums[u] = getFather(nums, nums[u]);
        }
        return nums[u];
    }

    private static void quickSort(List<Edge> edges) {
        quickSort(edges, 0, edges.size() - 1);
    }

    private static void quickSort(List<Edge> edges, int left, int right) {
        if (left >= right) return;

        int i = left, j = right, temp = edges.get(left).w;
        while (i != j) {
            while (edges.get(j).w >= temp && i < j) {
                j--;
            }
            while (edges.get(i).w <= temp && i < j) {
                i++;
            }
            if (i < j) {
                Collections.swap(edges, i, j);
            }
        }

        Collections.swap(edges, left, i);

        quickSort(edges, left, i - 1);
        quickSort(edges, i + 1, right);
    }
}
