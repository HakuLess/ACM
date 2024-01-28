package leetcode.contest.c382

import utils.print
import kotlin.math.sqrt

fun main() {
    val s = SolutionB()
    s.maximumLength(intArrayOf(5, 4, 1, 2, 2)).print()
    s.maximumLength(intArrayOf(1, 3, 2, 4)).print()
    s.maximumLength(intArrayOf(1, 1)).print()
}

class SolutionB {
    fun maximumLength(nums: IntArray): Int {
        val ones = nums.count { it == 1 }
        val ans = ones + ones % 2 - 1

        val map = HashMap<Int, Int>()
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        val set = nums.toHashSet()
        val dp = HashMap<Int, Int>()
        nums.sort()
        set.sorted().forEach {
            val sqrt = sqrt(it.toDouble())
            val cnt = map.getOrDefault(sqrt.toInt(), 0)
            if (sqrt.let { it - it.toInt() != 0.0 } || cnt < 2) {
                dp[it] = 1
            } else {
                dp[it] = dp.getOrDefault(sqrt.toInt(), 0) + 1
            }
        }
        return maxOf(ans, dp.values.maxOrNull()!! * 2 - 1)
    }
}