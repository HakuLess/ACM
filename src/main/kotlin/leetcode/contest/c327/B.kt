package leetcode.contest.c327

import java.util.*

class SolutionB {
    fun maxKelements(nums: IntArray, k: Int): Long {
        val pq = PriorityQueue<Int>(compareBy { -it })
        nums.forEach {
            pq.offer(it)
        }
        var c = 0
        var ans = 0L
        while (c != k) {
            c++
            val item = pq.poll()
            ans += item
            pq.offer((item + 2) / 3)
        }
        return ans
    }
}