package com.example.leet.d_search.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mingxxx on 2017/4/17.
 */

public class Solution {

  static String[] LETTERS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

  /**
   * 单词搜索
   * 给出一个二维的字母板和一个单词，寻找字母板网格中是否存在这个单词。
   * 单词可以由按顺序的相邻单元的字母组成，其中相邻单元指的是水平或者垂直方向相邻。每个单元中的字母最多只能使用一次。
   * 样例
   * 给出board =
   * [
   * "ABCE",
   * "SFCS",
   * "ADEE"
   * ]
   * word = "ABCCED"， ->返回 true,
   * word = "SEE"，-> 返回 true,
   * word = "ABCB"， -> 返回 false.
   *
   * @param board: A list of lists of character
   * @param word: A string
   * @return: A boolean
   */
  public static boolean exist(char[][] board, String word) {
    if (board == null || word == null || word.length() == 0) return false;
    boolean[][] marks = new boolean[board.length][board[0].length];
    int[][] steps = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };
    for (int i = 0; i < board.length; i++) {
      char[] chars = board[i];
      for (int j = 0; j < chars.length; j++) {
        if (findWord(board, marks, i, j, steps, word, 0)) return true;
      }
    }
    return false;
  }

  private static boolean findWord(char[][] board, boolean[][] marks, int i, int j, int[][] steps,
      String word, int step) {
    if (word.charAt(step) != board[i][j]) {
      return false;
    }
    if (step == word.length() - 1) {
      return true;
    }
    marks[i][j] = true; //注意第一个字符也要标记  所以放在循环外面
    for (int k = 0; k < steps.length; k++) {
      int newI = i + steps[k][0];
      int newJ = j + steps[k][1];
      if (newI >= board.length || newI < 0 || newJ >= board[newI].length || newJ < 0) {
        continue;
      }
      if (marks[newI][newJ]) continue;
      if (findWord(board, marks, newI, newJ, steps, word, step + 1)) {
        return true;
      }
    }
    marks[i][j] = false;
    return false;
  }

  /**
   * 岛屿的个数
   * 给一个01矩阵，求不同的岛屿的个数。
   * 0代表海，1代表岛，如果两个1相邻，那么这两个1属于同一个岛。我们只考虑上下左右为相邻。
   * 样例
   * 在矩阵：
   * [
   * [1, 1, 0, 0, 0],
   * [0, 1, 0, 0, 1],
   * [0, 0, 0, 1, 1],
   * [0, 0, 0, 0, 0],
   * [0, 0, 0, 0, 1]
   * ]
   * 中有 3 个岛.
   *
   * @param grid a boolean 2D matrix
   * @return an integer
   */
  public static int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) return 0;
    boolean[][] marks = new boolean[grid.length][grid[0].length];
    int[][] steps = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };
    int num = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (!marks[i][j] && grid[i][j] == '1') {
          markIslands(grid, marks, steps, i, j);
          num++;
        }
      }
    }
    return num;
  }

  private static void markIslands(char[][] grid, boolean[][] marks, int[][] steps, int i, int j) {
    marks[i][j] = true; //注意第一个字符也要标记  所以放在循环外面
    for (int k = 0; k < steps.length; k++) {
      int newI = i + steps[k][0];
      int newJ = j + steps[k][1];
      if (newI >= grid.length || newI < 0 || newJ >= grid[newI].length || newJ < 0) {
        continue;
      }
      if (!marks[newI][newJ] && grid[newI][newJ] == '1') {
        markIslands(grid, marks, steps, newI, newJ);
      }
    }
  }

  public static void main(String[] args) {
    char[][] chars = {
        { '1', '1', '1', '1', '0' },
        { '1', '1', '0', '1', '0' },
        { '1', '1', '0', '0', '0' },
        { '0', '0', '0', '0', '0' }
    };
    System.out.println(numIslands(chars));
  }

  /**
   * 电话号码的字母组合
   * 给一个不包含01的数字字符串，每个数字代表一个字母，请返回其所有可能的字母组合。
   * 下图的手机按键图，就表示了每个数字可以代表的字母。
   * 样例
   * 给定 "23"
   * 返回 ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
   *
   * @param digits A digital string
   * @return all posible letter combinations
   */
  //时间复杂度3^n===O(2^n)
  public ArrayList<String> letterCombinations(String digits) {
    ArrayList<String> strs = new ArrayList<>();
    if (digits != null
        && digits.trim()
                 .length() > 0) {
      findCombinations(strs, digits, 0, "");
    }
    return strs;
  }

  private void findCombinations(ArrayList<String> strs, String digits, int index, String s) {
    if (index == digits.length()) {
      strs.add(s);
      return;
    }
    char c = digits.charAt(index);
    String temp = LETTERS[c - '0'];
    for (int i = 0; i < temp.length(); i++) {
      findCombinations(strs, digits, index + 1, s + temp.charAt(i));
    }
  }

  /**
   * 给定一个包含不同数字的数组，求出所有可能的排列数组
   *
   * For example,
   * [1,2,3] have the following permutations:
   * [
   * [1,2,3],
   * [1,3,2],
   * [2,1,3],
   * [2,3,1],
   * [3,1,2],
   * [3,2,1]
   * ]
   *
   * @param nums
   * @return
   */
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> lists = new ArrayList<>();
    if (nums != null && nums.length > 0) {
      LinkedList<Integer> list = new LinkedList<>();
      boolean[] marks = new boolean[nums.length];
      findPermute(lists, nums, list, marks);
    }
    return lists;
  }

  private void findPermute(List<List<Integer>> lists, int[] nums, LinkedList<Integer> list,
      boolean[] marks) {
    if (list.size() == nums.length) {
      lists.add(new ArrayList<>(list));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (!marks[i]) {
        list.addLast(nums[i]);
        marks[i] = true;
        findPermute(lists, nums, list, marks);
        list.removeLast();
        marks[i] = false;
      }
    }
  }

  /**
   * 给定一个可能包含相同数字的数组，求出所有可能的排列数组
   *
   * For example,
   * [1,1,2] have the following permutations:
   * [
   * [1,1,2],
   * [1,2,1],
   * [2,1,1]
   * ]
   *
   * @param nums
   * @return
   */
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> lists = new ArrayList<>();
    if (nums != null && nums.length > 0) {
      LinkedList<Integer> list = new LinkedList<>();
      boolean[] marks = new boolean[nums.length];
      Arrays.sort(nums);
      findPermuteUnique(lists, nums, list, marks);
    }
    return lists;
  }

  private void findPermuteUnique(List<List<Integer>> lists, int[] nums, LinkedList<Integer> list,
      boolean[] marks) {
    if (list.size() == nums.length) {
      lists.add(new ArrayList<>(list));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (marks[i]) continue;
      if (i > 0 && nums[i - 1] == nums[i] && marks[i - 1]) continue;
      list.addLast(nums[i]);
      marks[i] = true;
      findPermuteUnique(lists, nums, list, marks);
      list.removeLast();
      marks[i] = false;
    }
  }

  /**
   * 组合
   * 组给出两个整数n和k，返回从1......n中选出的k个数的组合。
   * 样例
   * 例如 n = 4 且 k = 2
   * 返回的解为：
   * [[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
   *
   * @param n: Given the range of numbers
   * @param k: Given the numbers of combinations
   * @return: All the combinations of k numbers out of 1..n
   */
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> lists = new ArrayList<>();
    if (n > 0 && k > 0 && n > k) {
      LinkedList<Integer> list = new LinkedList<>();
      findCombine(lists, n, k, list, 1);
    }
    return lists;
  }

  private void findCombine(List<List<Integer>> lists, int n, int k, LinkedList<Integer> list,
      int start) {
    if (list.size() == k) {
      lists.add(new ArrayList<>(list));
      return;
    }
    // 还有k - list.size()个空位, 所以,[i...n]中至少要有k-list.size()个元素
    // i最多为 n - (k-list.size()) + 1
    for (int i = start; i <= n - (k - list.size()) + 1; i++) {
      list.addLast(i);
      findCombine(lists, n, k, list, i + 1);
      list.removeLast();
    }
  }

  /**
   * N皇后问题
   * n皇后问题是将n个皇后放置在n*n的棋盘上，皇后彼此之间不能相互攻击(所有的横向、竖向和斜向不会有两个以上)。
   * 给定一个整数n，返回所有不同的n皇后问题的解决方案。
   * 每个解决方案包含一个明确的n皇后放置布局，其中“Q”和“.”分别表示一个女王和一个空位置。
   *
   * 样例
   * 对于4皇后问题存在两种解决的方案：
   * [
   * [".Q..", // Solution 1
   * "...Q",
   * "Q...",
   * "..Q."],
   * ["..Q.", // Solution 2
   * "Q...",
   * "...Q",
   * ".Q.."]
   * ]
   * Get all distinct N-Queen solutions
   *
   * @param n: The number of queens
   * @return: All distinct solutions
   * For example, A string '...Q' shows a queen on forth position
   */
  List<List<String>> solveNQueens(int n) {
    List<List<String>> lists = new ArrayList<>();
    LinkedList<Integer> mark = new LinkedList<>();
    HashSet<Integer> heng = new HashSet();
    HashSet<Integer> pie = new HashSet();
    HashSet<Integer> na = new HashSet();
    putQueens(n, mark, 0, lists, heng, pie, na);
    return lists;
  }

  private void putQueens(int n, LinkedList<Integer> mark, int index, List<List<String>> lists,
      HashSet<Integer> heng, HashSet<Integer> pie, HashSet<Integer> na) {
    if (index == n) {
      ArrayList<String> list = new ArrayList<>();
      for (int i = 0; i < mark.size(); i++) {
        String s = "";
        for (int j = 0; j < n; j++) {
          if (j == mark.get(i)) {
            s += "Q";
          } else {
            s += ".";
          }
        }
        list.add(s);
      }
      lists.add(list);
      return;
    }
    for (int i = 0; i < n; i++) {
      if (heng.contains(i) || pie.contains(i + index) || na.contains(index - i + n - 1)) continue;
      heng.add(i);
      pie.add(i + index);
      na.add(index - i + n - 1);
      mark.addLast(i);
      putQueens(n, mark, index + 1, lists, heng, pie, na);
      heng.remove(i);
      pie.remove(i + index);
      na.remove(index - i + n - 1);
      mark.removeLast();
    }
  }
}
