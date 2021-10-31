package leetcode.contest.c265

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
    fun nodesBetweenCriticalPoints(head: ListNode?): IntArray {
        val arr = head.toIntArray()
        val l = arrayListOf<Int>()
        for (i in arr.indices) {
            if (i == 0 || i == arr.lastIndex) continue
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                l.add(i)
            } else if (arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) {
                l.add(i)
            }
        }
        if (l.size < 2) return intArrayOf(-1, -1)
        var min = arr.size
        val max = l.last() - l.first()
        for (i in l.indices) {
            if (i == 0) continue
            min = minOf(min, l[i] - l[i - 1])
        }
        return intArrayOf(min, max)
    }
}