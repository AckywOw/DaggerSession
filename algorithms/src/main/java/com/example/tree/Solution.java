package com.example.tree;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * 二叉树的前序遍历
     * @param root: The root of binary tree.
     * @return: Preorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> preorder = new ArrayList<Integer>();

        if (root == null) {
            return preorder;
        }

        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            preorder.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return preorder;
    }

    /**
     * 二叉树的中序遍历
     * @param root: The root of binary tree.
     * @return: Inorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        // write your code here
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        TreeNode curt = root;
        while (curt != null || !stack.empty()) {
            while (curt != null) {
                stack.add(curt);
                curt = curt.left;
            }
            curt = stack.peek();
            stack.pop();
            result.add(curt.val);
            curt = curt.right;
        }
        return result;
    }

    /**
     * 二叉树的后序遍历
     * @param root: The root of binary tree.
     * @return: Postorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();   
        if (root == null) {
            return result;
        }
        result.addAll(postorderTraversal(root.left));
        result.addAll(postorderTraversal(root.right));
        result.add(root.val);
        return result;
    }
}
