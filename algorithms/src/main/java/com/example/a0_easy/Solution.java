package com.example.a0_easy;

/**
 * 删除链表中的元素
 * 给出链表 1->2->3->3->4->5->3, 和 val = 3, 你需要返回删除3之后的链表：1->2->4->5。
 * Created by AckywOw on 2016/7/29.
 */
public class Solution {
    /**
     * @param head a ListNode
     * @param val  an integer
     * @return a ListNode
     */
    public ListNode removeElements(ListNode head, int val) {
        // Write your code here
        if (head == null)
            return null;
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
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
} 
