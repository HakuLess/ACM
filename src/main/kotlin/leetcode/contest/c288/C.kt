package leetcode.contest.c288

import java.util.*

class SolutionC {
    fun maximumProduct(nums: IntArray, k: Int): Int {
        val mod = 1000000007L
        var ans = 1L
        val pq = PriorityQueue<Int>()
        nums.forEach {
            pq.offer(it)
        }
        var l = k
        while (l != 0) {
            l--
            val item = pq.poll()
            pq.offer(item + 1)
        }
        pq.forEach {
            ans *= it
            ans %= mod
        }
        return ans.toInt()
    }
}