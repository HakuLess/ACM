package leetcode.contest.b69

import utils.ListNode
import utils.toIntArray

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
    fun pairSum(head: ListNode?): Int {
        val arr = head.toIntArray()
        val n = arr.size
        var ans = Int.MIN_VALUE
        for (i in arr.indices) {
            ans = maxOf(ans, arr[i] + arr[n - i - 1])
        }
        return ans
    }
}