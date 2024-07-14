package leetcode.contest.c406

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
    fun modifiedList(nums: IntArray, head: ListNode?): ListNode? {
        val arr = ArrayList(head.toIntArray().toList())
        val set = nums.toHashSet()
        for (i in arr.indices.reversed()) {
            if (arr[i] in set) {
                arr.removeAt(i)
            }
        }
        return arr.toIntArray().toListNode()
    }
}