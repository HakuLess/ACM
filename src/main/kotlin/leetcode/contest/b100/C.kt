package leetcode.contest.b100

import java.util.*
import kotlin.collections.HashSet

class SolutionC {
    fun findScore(nums: IntArray): Long {
        val set = HashSet<Int>()
        // value index
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy({ it.first }, { it.second }))
        for (i in nums.indices) {
            pq.offer(Pair(nums[i], i))
        }
        var ans = 0L
        while (pq.isNotEmpty()) {
            val cur = pq.poll()
            if (cur.second !in set) {
                set.add(cur.second)
                set.add(cur.second + 1)
                set.add(cur.second - 1)
                ans += cur.first
            }
        }
        return ans
    }
}