package leetcode.contest.b110

import utils.*

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
    val s = SolutionB()
    s.insertGreatestCommonDivisors(intArrayOf(18, 6, 10, 3).toListNode()).print()
}

class SolutionB {
    fun insertGreatestCommonDivisors(head: ListNode?): ListNode? {
        val l = ArrayList<Int>()
        l.addAll(head.toIntArray().toTypedArray())
        val n = l.size
        for (i in n - 1 downTo 1) {
            l.add(i, gcd(l[i - 1], l[i]))
        }
        return l.toIntArray().toListNode()
    }
}