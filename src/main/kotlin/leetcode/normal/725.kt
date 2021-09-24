package leetcode.normal

import utils.ListNode


class Solution725 {
    fun splitListToParts(head: ListNode?, k: Int): Array<ListNode?> {
        var n = 0
        var temp = head
        while (temp != null) {
            n++
            temp = temp.next
        }
        val a = n / k
        val b = n % k

        val ans = arrayOfNulls<ListNode>(k)
        var cur = head

        var i = 0
        while (i < k && cur != null) {
            ans[i] = cur
            val partSize = a + if (i < b) 1 else 0
            for (j in 1 until partSize) {
                cur = cur!!.next
            }
            val next = cur!!.next
            cur.next = null
            cur = next
            i++
        }
        return ans
    }
}