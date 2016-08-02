package com.example.queue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by AckywOw on 2016/7/31.
 */
public class Solution {
    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    /**
     * 二叉树的层次遍历
     * 给出一棵二叉树，返回其节点值的层次遍历（逐层从左往右访问）
     *
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write your code here
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        ArrayList<ArrayList<Integer>> tree = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return tree;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            tree.add(list);
        }
        return tree;
    }

    /**
     * 二叉树的层次遍历 II
     * 给出一棵二叉树，返回其节点值从底向上的层次序遍历（按从叶节点所在层到根节点所在的层遍历，然后逐层从左往右遍历）
     *
     * @param root: The root of binary tree.
     * @return: buttom-up level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        // write your code here
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        ArrayList<ArrayList<Integer>> tree = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return tree;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            tree.add(0, list);
        }
        return tree;
    }

    /**
     * 二叉树的锯齿形层次遍历
     * 给出一棵二叉树，返回其节点值的锯齿形层次遍历（先从左往右，下一层再从右往左，层与层之间交替进行）
     *
     * @param root: The root of binary tree.
     * @return: A list of lists of integer include
     * the zigzag level order traversal of its nodes' values
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // write your code here
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        ArrayList<ArrayList<Integer>> tree = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return tree;
        }
        stack1.push(root);
        boolean leftToRight = true;
        while (leftToRight && !stack1.isEmpty() || !leftToRight && !stack2.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            if (leftToRight) {
                int size = stack1.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = stack1.pop();
                    list.add(node.val);
                    if (node.left != null) {
                        stack2.push(node.left);
                    }
                    if (node.right != null) {
                        stack2.push(node.right);
                    }
                }
            } else {
                int size = stack2.size();
                for (int i = size - 1; i >= 0; i--) {
                    TreeNode node = stack2.pop();
                    list.add(node.val);
                    if (node.right != null) {
                        stack1.push(node.right);
                    }
                    if (node.left != null) {
                        stack1.push(node.left);
                    }
                }
            }
            tree.add(list);
            leftToRight = !leftToRight;
        }
        return tree;
    }
}
