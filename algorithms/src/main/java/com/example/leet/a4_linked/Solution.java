package com.example.leet.a4_linked;

/**
 * Created by AckywOw on 2016/7/31.
 */
public class Solution {

  /**
   * 在O(1)时间复杂度删除链表节点
   * 给定一个单链表中的一个等待被删除的节点(非表头或表尾)。请在在O(1)时间复杂度删除该链表节点。
   *
   * 给定 1->2->3->4，和节点 3，删除 3 之后，链表应该变为 1->2->4。
   *
   * @param node: the node in the list should be deleted
   * @return: nothing
   */
  public void deleteNode(ListNode node) {
    if (node == null) return;
    if (node.next == null) {
      node = null;
    } else {
      node.val = node.next.val;
      node.next = node.next.next;
    }
  }

  /**
   * 删除链表中的元素
   * 给出链表 1->2->3->3->4->5->3, 和 val = 3, 你需要返回删除3之后的链表：1->2->4->5。
   * Created by AckywOw on 2016/7/29.
   *
   * @param head a ListNode
   * @param val an integer
   * @return a ListNode
   */
  public ListNode removeElements(ListNode head, int val) {
    // Write your code here
    if (head == null) return null;
    ListNode first = head, last = head.next;
    while (last != null) {
      if (last.val == val) {
        first.next = last.next;
        last = first.next;
      } else {
        first = first.next;
        last = last.next;
      }
    }
    if (head.val == val) {
      head = head.next;
    }
    return head;
  }

  /**
   * 删除链表中的元素
   * 给出链表 1->2->3->3->4->5->3, 和 val = 3, 你需要返回删除3之后的链表：1->2->4->5。
   * Created by AckywOw on 2016/7/29.
   *
   * @param head a ListNode
   * @param val an integer
   * @return a ListNode
   */
  public ListNode removeElements2(ListNode head, int val) {
    // Write your code here
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    ListNode cur = dummyHead;
    while (cur.next != null) {
      if (cur.next.val == val) {
        cur.next = cur.next.next;
      } else {
        cur = cur.next;
      }
    }
    return dummyHead.next;
  }

  /**
   * 两两交换链表中的节点
   * 给一个链表，两两交换其中的节点，然后返回交换后的链表。
   *
   * 给出 1->2->3->4, 你应该返回的链表是 2->1->4->3。
   *
   * @param head a ListNode
   * @return a ListNode
   */
  public ListNode swapPairs(ListNode head) {
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    ListNode cur = dummyHead;
    while (cur.next != null && cur.next.next != null) {
      ListNode n1 = cur.next;
      ListNode n2 = cur.next.next;
      n1.next = n2.next;
      n2.next = n1;
      cur.next = n2;
      cur = n1;
    }
    return dummyHead.next;
  }

  /**
   * 链表求和
   * 你有两个用链表代表的整数，其中每个节点包含一个数字。数字存储按照在原来整数中相反的顺序，使得第一个数字位于链表的开头。写出一个函数将两个整数相加，用链表形式返回和。
   * 给出两个链表 3->1->5->null 和 5->9->2->null，返回 8->0->8->null
   *
   * @param l1: the first list
   * @param l2: the second list
   * @return: the sum list of l1 and l2
   */
  public ListNode addLists(ListNode l1, ListNode l2) {
    // write your code here
    if (l1 == null || l2 == null) {
      return null;
    }
    ListNode head = new ListNode(0);
    ListNode point = head;
    int carry = 0;//入位
    while (l1 != null && l2 != null) {
      int sum = carry + l1.val + l2.val;
      point.next = new ListNode(sum % 10);
      carry = sum / 10;
      l1 = l1.next;
      l2 = l2.next;
      point = point.next;
    }

    while (l1 != null) {
      int sum = carry + l1.val;
      point.next = new ListNode(sum % 10);
      carry = sum / 10;
      l1 = l1.next;
      point = point.next;
    }

    while (l2 != null) {
      int sum = carry + l2.val;
      point.next = new ListNode(sum % 10);
      carry = sum / 10;
      l2 = l2.next;
      point = point.next;
    }

    if (carry != 0) {
      point.next = new ListNode(carry);
    }

    return head.next;
  }

  /**
   * 翻转一个链表
   * 给出一个链表1->2->3->null，这个翻转后的链表为3->2->1->null
   *
   * @param head: The head of linked list.
   * @return: The new head of reversed linked list.
   */
  public ListNode reverse(ListNode head) {
    // write your code here
    ListNode prev = null;
    while (head != null) {
      ListNode temp = head.next;
      head.next = prev;
      prev = head;
      head = temp;
    }
    return prev;
  }

  /**
   * 带环链表
   * 给定一个链表，判断它是否有环。
   * 给出 -21->10->4->5, tail connects to node index 1，返回 true
   *
   * @param head: The first node of linked list.
   * @return: True if it has a cycle, or false
   */
  public boolean hasCycle(ListNode head) {
    // write your code here
    if (head == null || head.next == null) {
      return false;
    }
    ListNode fast = head.next;
    ListNode slow = head;
    while (fast != slow) {
      if (fast == null || fast.next == null) {
        return false;
      }
      fast = fast.next.next;
      slow = slow.next;
    }
    return true;
  }

  /**
   * 带环链表II
   * 给定一个链表，如果链表中存在环，则返回到链表中环的起始节点的值，如果没有环，返回null。
   * 给出 -21->10->4->5, tail connects to node index 1，返回10
   *
   * @param head: The first node of linked list.
   * @return: The node where the cycle begins.
   * if there is no cycle, return null
   */
  public ListNode detectCycle(ListNode head) {
    // write your code here
    if (head == null || head.next == null) {
      return null;
    }
    ListNode fast = head.next;
    ListNode slow = head;
    while (fast != slow) {
      if (fast == null || fast.next == null) {
        return null;
      }
      fast = fast.next.next;
      slow = slow.next;
    }
    while (head != slow.next) {
      head = head.next;
      slow = slow.next;
    }
    return head;
  }

  /**
   * 删除链表中倒数第n个节点
   * 给定一个链表，删除链表中倒数第n个节点，返回链表的头节点。链表中的节点个数大于等于n
   *
   * 给出链表1->2->3->4->5->null和 n = 2.
   * 删除倒数第二个节点之后，这个链表将变成1->2->3->5->null.
   *
   * @param head: The first node of linked list.
   * @param n: An integer.
   * @return: The head of linked list.
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    ListNode p = dummyHead;
    ListNode q = p.next;
    for (int i = 0; i < n; i++) {
      q = q.next;
    }
    while (q != null) {
      p = p.next;
      q = q.next;
    }
    p.next = p.next.next;
    return dummyHead.next;
  }
}
