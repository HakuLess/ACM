package leetcode.normal

import utils.ListNode

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution1290 {
    fun getDecimalValue(head: ListNode?): Int {
        var ans = 0
        var cur = head
        while (cur != null) {
            ans *= 2
            if (cur.`val` == 1) {
                ans++
            }
            cur = cur.next
        }
        return ans
    }
}