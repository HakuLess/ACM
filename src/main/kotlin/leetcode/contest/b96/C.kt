package leetcode.contest.b96

import utils.print
import java.util.*

fun main() {
    val s = SolutionC()
    // 168
    s.maxScore(intArrayOf(2, 1, 14, 12), intArrayOf(11, 7, 13, 6), 3).print()
}

class SolutionC {
    fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {
        val ids = nums1.indices.sortedByDescending { nums2[it] }
        val pq = PriorityQueue<Int>(compareBy { it })
        var sum = 0L
        var ans = 0L
        for (i in ids) {
            val a = nums2[i].toLong()
            pq.offer(nums1[i])
            sum += nums1[i]
            if (pq.size > k) {
                val item = pq.poll()
                sum -= item
            }
            if (pq.size == k) {
                ans = maxOf(ans, a * sum)
            }
        }
        return ans
    }
}