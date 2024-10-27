package leetcode.contest.c421

import utils.gcd
import utils.print

fun main() {
    val s = SolutionC()
    s.subsequencePairCount(intArrayOf(1, 2, 3, 4)).print()
}

class SolutionC {
    fun subsequencePairCount(nums: IntArray): Int {

        val mod = 1_000_000_007

        fun helper(l: Int, r: Int, pos: Int, nums: IntArray, dp: Array<Array<Array<Int>>>): Int {
            if (pos == nums.size) {
                // 如果 l 和 r 都不为 0 且相等，返回 1
                return if (l != 0 && r != 0 && l == r) 1 else 0
            } else if (dp[l][r][pos] == -1) {
                var res = 0
                // 当前数字不加入子序列
                res = (res + helper(l, r, pos + 1, nums, dp)) % mod
                // 当前数字加入左子序列
                res = if (l == 0) {
                    (res + helper(nums[pos], r, pos + 1, nums, dp)) % mod
                } else {
                    (res + helper(gcd(l, nums[pos]), r, pos + 1, nums, dp)) % mod
                }
                // 当前数字加入右子序列
                res = if (r == 0) {
                    (res + helper(l, nums[pos], pos + 1, nums, dp)) % mod
                } else {
                    (res + helper(l, gcd(nums[pos], r), pos + 1, nums, dp)) % mod
                }
                dp[l][r][pos] = res
            }
            return dp[l][r][pos]
        }

        // dp 为达到第 i 个位置，两个子序列的当前 GCD 值
        val dp = Array(201) { Array(201) { Array<Int>(nums.size) { -1 } } }
        // 返回结果
        return helper(0, 0, 0, nums, dp)
    }
}