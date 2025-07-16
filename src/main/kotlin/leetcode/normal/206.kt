package leetcode.normal

import utils.ListNode
import utils.print
import utils.toListNode

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

fun main() {
    val s = Solution206()
    s.reverseList(intArrayOf(1, 2, 3, 4, 5).toListNode()).print()
}

class Solution206 {
    fun reverseList(head: ListNode?): ListNode? {
        var pre: ListNode? = null
        var cur = head
        while (cur != null) {
            val next = cur.next
            cur.next = pre
            pre = cur
            cur = next
        }
        return pre
    }
}