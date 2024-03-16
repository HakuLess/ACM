package leetcode.contest.b126

import java.util.*
import kotlin.collections.HashSet

class SolutionB {
    fun unmarkedSumArray(nums: IntArray, queries: Array<IntArray>): LongArray {
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy({ it.first }, { it.second }))
        for (i in nums.indices) {
            pq.offer(Pair(nums[i], i))
        }
        val ans = LongArray(queries.size)
        var cur = 0L
        nums.forEach {
            cur += it
        }
        val set = HashSet<Int>()

        for (i in queries.indices) {
            val (index, k) = queries[i]

            if (index !in set) {
                set.add(index)
                cur -= nums[index]
            }

            var r = 0
            while (r != k && pq.isNotEmpty()) {
                val (value, index) = pq.poll()
                if (index in set) continue

                set.add(index)
                cur -= value
                r++
            }

            ans[i] = cur
        }
        return ans
    }
}