package com.example.leet.f_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by AckywOw on 2016/6/21.
 */
public class Dump {

    public static void main(String[] args) {
        Integer[] nums = {99, 5, 36, 7, 22, 17, 46, 12, 2, 19, 25, 28, 1, 92};
        LinkedList<Integer> heap = new LinkedList<Integer>(Arrays.asList(nums));
        creatHeap(heap);
        System.out.println(heap.toString());

        List<Integer> sortHeap = new ArrayList<>();
        int length = heap.size();
        for (int i = 0; i < length; i++) {
            sortHeap.add(deleteMax(heap));
        }
        System.out.println(sortHeap.toString());
    }

    private static int deleteMax(LinkedList<Integer> heap) {
        int t = heap.getFirst();
        heap.set(0, heap.getLast());
        heap.removeLast();
        siftDown(heap, 0);
        return t;
    }

    private static void creatHeap(LinkedList<Integer> heap) {
        for (int i = heap.size() / 2; i >= 0; i--) {
            siftDown(heap, i);
        }
    }

    private static void siftDown(LinkedList<Integer> heap, int i) {
        int t;
        boolean ok = false;
        while (i * 2 + 1 < heap.size() && !ok) {
            t = heap.get(i) > heap.get(i * 2 + 1) ? i * 2 + 1 : i;
            if (i * 2 + 2 < heap.size() && heap.get(t) > heap.get(i * 2 + 2)) {
                t = i * 2 + 2;
            }
            if (t != i) {
                swap(heap, i, t);
                i = t;
            } else {
                ok = true;
            }
        }
    }

    private static void swap(LinkedList<Integer> arr, int i, int j) {
        Collections.swap(arr, i, j);
    }
}
