package leetcode.normal

import utils.ListNode

class Solution83 {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        var cur = head
        while (cur?.next != null) {
            if (cur.`val` == cur.next?.`val`) {
                cur.next = cur.next?.next
            } else {
                cur = cur.next
            }
        }
        return head
    }
}