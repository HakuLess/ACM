package leetcode.contest.c349

import utils.print

fun main() {
    val s = SolutionC()
    // 2714
    s.minCost(intArrayOf(288, 457, 953, 700, 464, 785, 203, 729, 725, 422), 76).print()
}

class SolutionC {
    fun minCost(nums: IntArray, x: Int): Long {
        val n = nums.size
        var cost = LongArray(n)
        val minCost = LongArray(n)
        for (i in nums.indices) {
            cost[i] = nums[i].toLong()
            minCost[i] = nums[i].toLong()
        }

        var ans = minCost.sum()

        var moveCost = 0L
        repeat(n - 1) {
            moveCost = x.toLong() * (it + 1)
            for (i in minCost.indices) {
                minCost[i] = minOf(cost[i], cost[(i + 1) % n])
            }
            cost = minCost.clone()
            ans = minOf(ans, minCost.sum() + moveCost)
        }
        return ans
    }
}