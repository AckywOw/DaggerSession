package com.example.a4_linked;


/**
 * Created by AckywOw on 2016/7/31.
 */
public class Solution {

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
}
