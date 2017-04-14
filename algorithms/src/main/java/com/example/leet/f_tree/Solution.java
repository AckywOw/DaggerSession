package com.example.leet.f_tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

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
   * 二叉树的前序遍历（递归）
   *
   * @param root: The root of binary tree.
   * @return: Preorder in ArrayList which contains node values.
   */
  public ArrayList<Integer> preorderTraversal(TreeNode root) {
    ArrayList<Integer> preorder = new ArrayList<Integer>();
    preorderTraversal(root, preorder);
    return preorder;
  }

  private void preorderTraversal(TreeNode root, ArrayList<Integer> preorder) {
    if (root == null) return;
    preorder.add(root.val);
    preorderTraversal(root.left, preorder);
    preorderTraversal(root.right, preorder);
  }

  /**
   * 二叉树的前序遍历（非递归）
   *
   * @param root: The root of binary tree.
   * @return: Preorder in ArrayList which contains node values.
   */
  public ArrayList<Integer> preorderTraversal2(TreeNode root) {
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
   * 二叉树的前序遍历（非递归）
   *
   * @param root: The root of binary tree.
   * @return: Preorder in ArrayList which contains node values.
   */
  public ArrayList<Integer> preorderTraversal3(TreeNode root) {
    Stack<Command> stack = new Stack<Command>();
    ArrayList<Integer> preorder = new ArrayList<Integer>();
    if (root == null) {
      return preorder;
    }
    stack.push(new Command(Command.GO, root));
    while (!stack.empty()) {
      Command command = stack.pop();
      if (Command.PRINT == command.order) {
        preorder.add(command.node.val);
      } else {
        if (command.node.right != null) {
          stack.push(new Command(Command.GO, command.node.right));
        }
        if (command.node.left != null) {
          stack.push(new Command(Command.GO, command.node.left));
        }
        stack.push(new Command(Command.PRINT, command.node));
      }
    }
    return preorder;
  }

  /**
   * 二叉树的中序遍历
   *
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
   *
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
      //tree.add(0, list); 这里就会变成自底向上的层次遍历
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

  /**
   * 完美平方
   * 给一个正整数 n, 找到若干个完全平方数(比如1, 4, 9, ... )使得他们的和等于 n。你需要让平方数的个数最少。
   * 给出 n = 12, 返回 3 因为 12 = 4 + 4 + 4。
   * 给出 n = 13, 返回 2 因为 13 = 4 + 9。
   *
   * @param n a positive integer
   * @return an integer
   */
  public int numSquares(int n) {

    if (n == 0) return 0;
    LinkedList<int[]> list = new LinkedList<int[]>();
    list.offer(new int[] { n, 0 });
    boolean[] book = new boolean[n + 1];
    book[n] = true;
    while (!list.isEmpty()) {
      int num = list.peek()[0];
      int step = list.peek()[1];
      list.pop();
      int i = 1;
      do {
        int a = num - i * i;
        if (a < 0) break;
        if (!book[a]) {
          if (a == 0) return step + 1;
          list.offer(new int[] { a, step + 1 });
          book[a] = true;
        }
        i++;
      } while (true);
    }
    throw new IllegalArgumentException("No solution!");
  }

  /**
   * 给定一个非空数组，返回前K个出现频率最高的元素
   * 如[1,1,1,2,2,3]，k=2
   * 返回[1,2]
   *
   * @param nums
   * @param k
   * @return
   */
  public List<Integer> topKFrequent(int[] nums, int k) {
    class Pair {
      int freq;
      int num;

      public Pair(int freq, int num) {
        this.freq = freq;
        this.num = num;
      }
    }
    class PairComparator implements Comparator<Pair> {

      @Override
      public int compare(Pair p1, Pair p2) {
        return p1.freq - p2.freq;
      }
    }
    HashMap<Integer, Integer> freqs = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (freqs.containsKey(nums[i])) {
        freqs.put(nums[i], freqs.get(nums[i]) + 1);
      } else {
        freqs.put(nums[i], 1);
      }
    }

    PriorityQueue<Pair> queue = new PriorityQueue<>(new PairComparator());
    for (Map.Entry<Integer, Integer> entry : freqs.entrySet()) {
      if (queue.size() == k) {
        if (entry.getValue() > queue.peek().freq) {
          queue.poll();
          queue.offer(new Pair(entry.getValue(), entry.getKey()));
        }
      } else {
        queue.offer(new Pair(entry.getValue(), entry.getKey()));
      }
    }
    List<Integer> list = new ArrayList<>();
    while (!queue.isEmpty()) {
      list.add(0, queue.poll().num);
    }
    return list;
  }

  private static class Command {
    static final int GO = 0;
    static final int PRINT = 1;
    int order;
    TreeNode node;

    public Command(int order, TreeNode node) {
      this.order = order;
      this.node = node;
    }
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
