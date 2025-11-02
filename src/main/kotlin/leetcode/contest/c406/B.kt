package leetcode.contest.c406

import utils.ListNode

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
        val set = nums.toHashSet()
        var ans = head

        // 去掉需要删除的头节点
        while (ans != null && ans.`val` in set) {
            ans = ans.next
        }

        // 若链表为空
        if (ans == null) return null

        var cur = ans
        while (cur?.next != null) {
            if (cur.next!!.`val` in set) {
                cur.next = cur.next!!.next
            } else {
                cur = cur.next
            }
        }

        return ans
    }

//    fun modifiedList(nums: IntArray, head: ListNode?): ListNode? {
//        val arr = ArrayList(head.toIntArray().toList())
//        val set = nums.toHashSet()
//        for (i in arr.indices.reversed()) {
//            if (arr[i] in set) {
//                arr.removeAt(i)
//            }
//        }
//        return arr.toIntArray().toListNode()
//    }
}