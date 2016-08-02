package com.example.tree;

import java.util.Stack;

import javax.transaction.TransactionRequiredException;

/**
 * Created by AckywOw on 2016/8/1.
 */
public class Solution {
    /**
     * 二叉树的最大深度
     *
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepth(TreeNode root) {
        // write your code here
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void invertBinaryTree(TreeNode root) {
        // write your code here
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertBinaryTree(root.left);
        invertBinaryTree(root.right);
    }

    /**
     * 等价二叉树
     *
     * @param a, b, the root of binary trees.
     * @return true if they are identical, or false.
     */
    public boolean isIdentical(TreeNode a, TreeNode b) {
        // Write your code here
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null) {
            return a.val == b.val && isIdentical(a.left, b.left) && isIdentical(a.right, b.right);
        }
        return false;
    }

    /**
     * 二叉查找树迭代器
     */
    public class BSTIterator {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node;

        //@param root: The root of binary tree.
        public BSTIterator(TreeNode root) {
            // write your code here
            node = root;
        }

        //@return: True if there has next node, or false
        public boolean hasNext() {
            // write your code here
            return node != null || !stack.isEmpty();
        }

        //@return: return next node
        public TreeNode next() {
            // write your code here
            while (node != null) {
                stack.push(node.left);
                node = node.left;
            }
            node = stack.pop();
            TreeNode temp = node;
            node = node.right;
            return node;
        }
    }
}
