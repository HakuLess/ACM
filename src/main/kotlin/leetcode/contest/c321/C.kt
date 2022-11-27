package leetcode.contest.c321

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
class SolutionC {
    fun removeNodes(head: ListNode?): ListNode? {
        val arr = head.toIntArray()
        val l = ArrayList<Int>()
        for (i in arr.indices.reversed()) {
            if (l.isEmpty() || arr[i] >= l[0]) {
                l.add(0, arr[i])
            }
        }
        return l.toIntArray().toListNode()
    }
}