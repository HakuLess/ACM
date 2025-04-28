package leetcode.normal

import utils.ListNode

class Solution82 {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }

        val ans = ListNode(0)
        ans.next = head
        var cur: ListNode = ans

        while (cur.next != null && cur.next?.next != null) {
            // 若两个值相等，则持续找到下一个不想等的为止
            if (cur.next?.`val` == cur.next?.next?.`val`) {
                val x = cur.next?.`val`
                while (cur.next != null && cur.next?.`val` == x) {
                    cur.next = cur.next?.next
                }
            } else {
                cur = cur.next!!
            }
        }

        return ans.next
    }
}