package com.example.leet.a4_linked;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AckywOw on 2016/7/31.
 */
public class SortList {

  /**
   * 将两个排序链表合并为一个新的排序链表
   * 给出 1->3->8->11->15->null，2->null， 返回 1->2->3->8->11->15->null。
   *
   * @param ListNode l1 is the head of the linked list
   * @param ListNode l2 is the head of the linked list
   * @return: ListNode head of linked list
   */
  public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    // write your code here
    ListNode dummy = new ListNode(0);
    ListNode lastNode = dummy;
    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        lastNode.next = l1;
        l1 = l1.next;
      } else {
        lastNode.next = l2;
        l2 = l2.next;
      }
      lastNode = lastNode.next;
    }
    if (l1 != null) {
      lastNode.next = l1;
    } else {
      lastNode.next = l2;
    }
    return dummy.next;
  }

  /**
   * 合并k个排序链表
   * 合并k个排序链表，并且返回合并后的排序链表。尝试分析和描述其复杂度。
   * 给出3个排序链表[2->4->null,null,-1->null]，返回 -1->2->4->null
   *
   * @param lists: a list of ListNode
   * @return: The head of one sorted list.
   */
  public static ListNode mergeKLists(List<ListNode> lists) {
    // write your code here
    if (lists == null || lists.size() == 0) {
      return null;
    }
    while (lists.size() > 1) {
      List<ListNode> newList = new ArrayList<ListNode>();
      for (int i = 0; i + 1 < lists.size(); i += 2) {
        newList.add(mergeTwoLists(lists.get(i), lists.get(i + 1)));
      }
      if (lists.size() % 2 == 1) {
        newList.add(lists.get(lists.size() - 1));
      }
      lists = newList;
    }

    return lists.get(0);
  }

  public static void main(String[] args) {
    List<ListNode> newList = new ArrayList<ListNode>();
    newList.add(null);
    ListNode news = new ListNode(1);
    newList.add(news);
    mergeKLists(newList);
  }

  /**
   * 链表排序
   * 在 O(n log n) 时间复杂度和常数级的空间复杂度下给链表排序。
   *
   * @param head: The head of linked list.
   * @return: You should return the head of the sorted linked list,
   * using constant space complexity.
   */
  public ListNode sortList(ListNode head) {
    // write your code here
    if (head == null || head.next == null) {
      return head;
    }
    ListNode mid = findMid(head);
    ListNode right = sortList(mid.next);
    mid.next = null;
    ListNode left = sortList(head);
    return mergeTwoLists(left, right);
  }

  private ListNode findMid(ListNode head) {
    ListNode fast = head.next;
    ListNode slow = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    return slow;
  }

  /**
   * 两个链表的交叉
   * 请写一个程序，找到两个单链表最开始的交叉节点。
   *
   * @param headA: the first list
   * @param headB: the second list
   * @return: a ListNode
   */
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    // Write your code here
    return null;
  }
}
