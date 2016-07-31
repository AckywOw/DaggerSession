package com.example.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by AckywOw on 2016/7/30.
 */
public class Solution {

    /**
     * 两数之和
     *
     * @param numbers : An array of Integer
     * @param target  : target = numbers[index1] + numbers[index2]
     * @return : [index1 + 1, index2 + 1] (index1 < index2)
     */
    public static int[] twoSum(int[] numbers, int target) {
        // write your code here
        int[] result = new int[2];
        if (numbers == null && numbers.length <= 1) {
            return result;
        }
        HashMap<Integer, int[]> map = new HashMap<Integer, int[]>();
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            int[] values = map.get(num);
            if (values == null) {
                values = new int[]{i};
                map.put(target - num, values);
            } else {
                result[0] = values[0] + 1;
                result[1] = i + 1;
            }
        }
        return result;
    }

    /**
     * 两个数的和||
     * 给定一个排序数组，求出其中两个数的和等于指定target时，这两个数在原始数组中的下标,返回的下标从1开始
     *
     * @param numbers : An array of Integer
     * @param target  : target = numbers[index1] + numbers[index2]
     * @return : [index1 + 1, index2 + 1] (index1 < index2)
     */
    public static int[] twoSum2(int[] numbers, int target) {
        // write your code here
        int[] result = new int[2];
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                result[0] = left + 1;
                result[1] = right + 1;
                break;
            }
        }
        return result;
    }

    /**
     * 给出一个有n个整数的数组S，在S中找到三个整数a, b, c，找到所有使得a + b + c = 0的三元组。
     * 在三元组(a, b, c)，要求a <= b <= c。
     * 结果不能包含重复的三元组。
     *
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (numbers == null && numbers.length < 3) {
            return result;
        }
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length; i++) {
            int left = i + 1, right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[i] + numbers[left] + numbers[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    ArrayList<Integer> path = new ArrayList<Integer>();
                    path.add(numbers[i]);
                    path.add(numbers[left]);
                    path.add(numbers[right]);
                    if (!result.contains(path))
                        result.add(path);
                    right--;
                    left++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(threeSum(new int[]{2, 7, 11, 15}));
    }
}
