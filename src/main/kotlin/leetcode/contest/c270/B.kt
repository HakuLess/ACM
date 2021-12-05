package leetcode.contest.c270

import utils.ListNode
import utils.toArrayList
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
class SolutionB {
    fun deleteMiddle(head: ListNode?): ListNode? {
        val l = head.toIntArray().toArrayList()
        if (l.size == 0 || l.size == 1) return null
        l.removeAt(l.size / 2)
        return l.toIntArray().toListNode()
    }
}