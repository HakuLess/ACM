package leetcode.normal

import utils.ListNode
import utils.toIntArray
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
class Solution148 {
    fun sortList(head: ListNode?): ListNode? {
        if (head == null) return null
        return head.toIntArray().sorted().toIntArray().toListNode()
    }
}