package com.example.string;

import java.util.HashSet;

/**
 * Created by AckywOw on 2016/8/1.
 */
public class Solution {
    /**
     * 最长无重复字符的子串
     * 给定一个字符串，请找出其中无重复字符的最长子字符串。
     *
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        // write your code here
        if (s == null && s.length() == 0) {
            return 0;
        }
        HashSet<Character> set = new HashSet<Character>();
        int leftBound = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                while (leftBound < i && s.charAt(leftBound) != s.charAt(i)) {
                    set.remove(s.charAt(leftBound));
                    leftBound++;
                }
                leftBound++;
            } else {
                set.add(s.charAt(i));
                max = Math.max(max, i - leftBound + 1);
            }
        }

        return max;
    }
}
