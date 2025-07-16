package leetcode.normal

import utils.ListNode
import utils.print
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
fun main() {
    val s = Solution25()
    s.reverseKGroup(intArrayOf(1, 2, 3, 4, 5).toListNode(), 2).print()
    s.reverseKGroup(intArrayOf(1, 2, 3, 4, 5).toListNode(), 3).print()
}

class Solution25 {

    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {

        fun rotate(pre: ListNode, k: Int): ListNode? {
            val cur = pre.next
            var i = 1
            while (i < k && cur != null && cur.next != null) {
                val next = cur.next
                cur.next = next!!.next
                next.next = pre.next
                pre.next = next
                i++
            }
            return if (i == k) cur else null
        }

        val dummy = ListNode(0)
        dummy.next = head
        var pre: ListNode? = dummy
        while (pre != null) {
            // 若剩余节点不足，对 pre 赋值前，再重新反转回来
            pre = rotate(pre, k) ?: rotate(pre, k)
        }
        return dummy.next
    }


//    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
//
//        fun reverseList(pre: ListNode, k: Int): ListNode? {
//            val cur = pre.next
//            var i = 1
//            while (cur != null && i < k) {
//                val next = cur.next
//                cur.next = next?.next
//                next?.next = pre?.next
//                pre.next = next
//                i++
//            }
//            return if (i == k) pre else null
//        }
//
//        val dummy = ListNode(0)
//        dummy.next = head
//
//        var pre = dummy
//        while (pre.next != null) {
//            val post = reverseList(pre, k)
//            pre = if (post == null) {
//                reverseList(pre, k)
//                break
//            } else {
//                post
//            }
//        }
//        return dummy.next
//    }
}