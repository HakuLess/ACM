package leetcode.contest.c358

import utils.ListNode
import utils.toIntArray
import utils.toListNode
import java.math.BigInteger

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
    fun doubleIt(head: ListNode?): ListNode? {
        val num = (head.toIntArray().joinToString("").toBigInteger() * BigInteger.valueOf(2L)).toString().map {
            it - '0'
        }.toIntArray()
        return num.toListNode()
    }
}