package leetcode.contest.c280

import utils.print

fun main() {
    val s = SolutionD()
    s.maximumANDSum(intArrayOf(1, 2, 3, 4, 5, 6), 3).print()
}

// 状态压缩
// DP
class SolutionD {
    fun maximumANDSum(nums: IntArray, numSlots: Int): Int {
        var ans = 0
        val dp = IntArray(1 shl numSlots * 2)
        for (i in dp.indices) {
            val c = Integer.bitCount(i)
            if (c >= nums.size) continue
            for (j in 0 until numSlots * 2) {
                if (i and (1 shl j) == 0) {
                    val k = i or (1 shl j)
                    dp[k] = maxOf(dp[k], dp[i] + ((j / 2 + 1) and nums[c]))
                    ans = maxOf(ans, dp[k])
                }
            }
        }
        return ans
    }
}