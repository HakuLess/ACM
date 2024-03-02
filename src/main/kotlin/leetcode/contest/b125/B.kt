package leetcode.contest.b125

import java.util.*

class SolutionB {
    fun minOperations(nums: IntArray, k: Int): Int {
        val pq = PriorityQueue<Long>()
        nums.forEach {
            pq.offer(it.toLong())
        }
        var ans = 0
        while (pq.peek() < k) {
            val a = pq.poll()
            val b = pq.poll()
            pq.offer(minOf(a, b) * 2L + maxOf(a, b))
            ans++
        }
        return ans
    }
}