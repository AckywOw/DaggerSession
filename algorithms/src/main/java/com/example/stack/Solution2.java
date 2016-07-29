package com.example.stack;

import java.util.Stack;

/**
 * 有效的括号序列
 * 给定一个字符串所表示的括号序列，包含以下字符： '(', ')', '{', '}', '[' and ']'， 判定是否是有效的括号序列。
 * 括号必须依照 "()" 顺序表示， "()[]{}" 是有效的括号，但 "([)]"则是无效的括号。
 * Created by AckywOw on 2016/7/29.
 */
public class Solution2 {
    /**
     * @param s A string
     * @return whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
        // Write your code here
        Stack stack = new Stack();
        if (s.length() == 0) {
            return true;
        } else if (s.length() == 1) {
            return false;
        }
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                if (!stack.isEmpty() && stack.peek().equals('(')) {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (s.charAt(i) == ']') {
                if (!stack.isEmpty() && stack.peek().equals('[')) {
                    stack.pop();
                } else {
                    return false;
                }

            } else if (s.charAt(i) == '}') {
                if (!stack.isEmpty() && stack.peek().equals('{')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
