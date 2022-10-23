package leetcode.contest.c316

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionC()
    s.minCost(intArrayOf(1, 3, 5, 2), intArrayOf(2, 3, 1, 14)).print()
    s.minCost(intArrayOf(2, 2, 2, 2), intArrayOf(2, 3, 1, 14)).print()
}

class SolutionC {
    fun minCost(nums: IntArray, cost: IntArray): Long {
        fun helper(cur: Int): Long {
            var ans = 0L
            for (i in nums.indices) {
                ans += 1L * abs(nums[i] - cur) * cost[i]
            }
            return ans
        }

        var total = 0L
        cost.forEach {
            total += it
        }
        val arr = nums.mapIndexed { index, value -> Pair(value, cost[index]) }.sortedBy { it.first }
        var cur = 0L
        for (i in arr.indices) {
            cur += arr[i].second
            if (cur > total / 2) {
                return helper(arr[i].first)
            }
        }
        return -1
    }
}