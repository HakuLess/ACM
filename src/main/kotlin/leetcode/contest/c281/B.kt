package leetcode.contest.c281

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
class SolutionB {
    fun mergeNodes(head: ListNode?): ListNode? {
        if (head == null) return null
        val ans = ArrayList<Int>()
        var tmp = 0
        head.toIntArray().forEach {
            if (it == 0 && tmp != 0) {
                ans.add(tmp)
                tmp = 0
            } else {
                tmp += it
            }
        }
        return ans.toIntArray().toListNode()
    }
}