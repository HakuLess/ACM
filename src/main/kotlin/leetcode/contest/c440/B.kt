package leetcode.contest.c440

import java.util.*

class SolutionB {
    fun findMaxSum(nums1: IntArray, nums2: IntArray, k: Int): LongArray {
        val n = nums1.size

        data class Item(val num1: Int, val num2: Int, val index: Int)

        val items = Array(n) { i -> Item(nums1[i], nums2[i], i) }
        val sorted = items.sortedBy { it.num1 }
        val ans = LongArray(n)
        val minHeap = PriorityQueue<Int>()
        var cur = 0L

        var i = 0
        while (i < n) {
            val curV = sorted[i].num1
            var j = i
            while (j < n && sorted[j].num1 == curV) {
                ans[sorted[j].index] = cur
                j++
            }
            j = i
            while (j < n && sorted[j].num1 == curV) {
                val value = sorted[j].num2
                if (minHeap.size < k) {
                    minHeap.offer(value)
                    cur += value
                } else if (minHeap.peek() < value) {
                    cur += value - minHeap.poll()
                    minHeap.offer(value)
                }
                j++
            }
            i = j
        }
        return ans
    }
}