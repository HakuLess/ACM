package leetcode.contest.c434

import utils.print

fun main() {
    val s = SolutionC()
    // 2
    s.maxFrequency(intArrayOf(2, 8), 8).print()
    // 2
    s.maxFrequency(intArrayOf(1, 2, 3, 4, 5, 6), 1).print()
    // 4
    s.maxFrequency(intArrayOf(10, 2, 3, 4, 5, 5, 4, 3, 2, 2), 10).print()
    // 4
    s.maxFrequency(intArrayOf(9, 8, 9, 4, 4), 4).print()
    // 3
    s.maxFrequency(intArrayOf(2, 6, 2, 10, 3), 2).print()
}

class SolutionC {
    fun maxFrequency(nums: IntArray, k: Int): Int {
        var ans = 0
        val dpk = IntArray(nums.size)
        for (i in nums.indices) {
            if (nums[i] == k) {
                dpk[i] = if (i == 0) {
                    1
                } else {
                    dpk[i - 1] + 1
                }
            } else {
                dpk[i] = if (i == 0) {
                    0
                } else {
                    dpk[i - 1]
                }
            }
        }

        for (fake in 1..50) {
            val dp = IntArray(nums.size)

            for (i in nums.indices) {
                if (nums[i] == fake) {
                    dp[i] = if (i == 0) {
                        1
                    } else {
                        dp[i - 1] + 1
                    }
                } else if (nums[i] == k) {
                    if (i != 0) {
//                        println("$i: ans ${dp[i - 1]} + ${dpk.last()} - ${dpk[i - 1]}")
                        // 右侧更换为原始K
                        ans = maxOf(ans, dp[i - 1] + dpk.last() - dpk[i - 1])
                    }
                } else {
                    dp[i] = if (i == 0) 0 else dp[i - 1]
                }
                // 左侧更换为原始K
                dp[i] = maxOf(dp[i], dpk[i])
                ans = maxOf(ans, dp[i])
            }

//            dpk.print()
//            dp.print()
        }

        return ans
    }
}