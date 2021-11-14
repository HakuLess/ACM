package leetcode.contest.c267

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
    fun reverseEvenLengthGroups(head: ListNode?): ListNode? {
        val ans = ArrayList<Int>()
        val arr = head.toIntArray().toArrayList()
        var i = 0
        var l = 1
        while (i in arr.indices) {
            val add = arr.subList(i, minOf(arr.size, i + l))
            if (add.size % 2 == 0)
                ans.addAll(add.reversed())
            else ans.addAll(add)
            i += l
            l++
        }
        return ans.toIntArray().toListNode()
    }
}