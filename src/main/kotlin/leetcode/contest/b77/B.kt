package leetcode.contest.b77

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionB()
    s.minimumAverageDifference(intArrayOf(2, 5, 3, 9, 5, 3)).print()
}

class SolutionB {
    fun minimumAverageDifference(nums: IntArray): Int {
        val n = nums.size
        val preSum = LongArray(n + 1)
        for (i in 0 until n) {
            preSum[i + 1] = preSum[i] + nums[i]
        }
        val sum = preSum[n]
        var min = Long.MAX_VALUE
        var ans = -1
        for (i in 0 until n) {
            val left = preSum[i + 1] / (i + 1)
            val right = if (i == n - 1) 0 else (sum - preSum[i + 1]) / (n - i - 1)
            if (abs(left - right) < min) {
                min = abs(left - right)
                ans = i
            }
        }
        return ans
    }
}