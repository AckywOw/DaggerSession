package com.example.stack;

import java.util.Stack;

/**
 * 给定一个文档(Unix-style)的完全路径，请进行路径简化。
 * "/home/", => "/home"
 * "/a/./b/../../c/", => "/c"
 * 
 * Created by AckywOw on 2016/7/29.
 */
public class Solution {
    /**
     * @param path the original path
     * @return the simplified path
     */
    public String simplifyPath(String path) {
        // Write your code here
        Stack<String> stack = new Stack<String>();
        String[] list = path.split("/");
        for (String str : list) {
            if(str.equals(".") || str.length() ==0) {
                continue;
            } else if(!str.equals("..")) {
                stack.push(str);
            } else {
                if(!stack.isEmpty())
                    stack.pop();
            }
        }
        
        StringBuilder sb = new StringBuilder();
        Stack<String> temp = new Stack<String>();
        while (!stack.isEmpty())
            temp.push(stack.pop());
        while (!temp.isEmpty()) {
            sb.append("/"+temp.pop());
        }
        if(sb.length()==0) {
            sb.append("/");
        }
        return sb.toString();
    }
}
