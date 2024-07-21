package leetcode.contest.c407

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionD()
    s.minimumOperations(intArrayOf(3, 5, 1, 2), intArrayOf(4, 6, 2, 4)).print()
    s.minimumOperations(intArrayOf(1, 3, 2), intArrayOf(2, 1, 4)).print()
    // 5
    s.minimumOperations(intArrayOf(1, 1, 3, 4), intArrayOf(4, 1, 3, 2)).print()
    // 20
    s.minimumOperations(intArrayOf(9, 2, 6, 10, 4, 8, 3, 4, 2, 3), intArrayOf(9, 5, 5, 1, 7, 9, 8, 7, 6, 5)).print()
}


// 子问题题目 1526 https://leetcode.cn/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/description/
class SolutionD {
    fun minimumOperations(nums: IntArray, target: IntArray): Long {
        val n = nums.size
        val diff = LongArray(n)
        for (i in 0 until n) {
            diff[i] = (target[i] - nums[i]).toLong()
        }
        val dp = LongArray(n)
        dp[0] = abs(diff[0])
        for (i in 1 until n) {
            dp[i] = dp[i - 1]
            if (diff[i] <= diff[i - 1] && diff[i - 1] <= 0) {
                dp[i] += diff[i - 1] - diff[i]
            } else if (diff[i] >= diff[i - 1] && diff[i - 1] >= 0) {
                dp[i] += diff[i] - diff[i - 1]
//            } else if ((diff[i - 1] <= 0 && diff[i] >= 0) || (diff[i - 1] >= 0 && diff[i] <= 0)) {
            } else if (diff[i - 1] * diff[i] <= 0) {
                dp[i] += abs(diff[i])
            }
        }
        return dp[n - 1]
    }

//    fun minimumOperations(nums: IntArray, target: IntArray): Long {
//        val n = nums.size
//        val det = LongArray(n)
//        for (i in 0 until n) {
//            det[i] = (target[i] - nums[i]).toLong()
//        }
//
//        // 返回值的符号
//        fun sgn(x: Long): Int {
//            return when {
//                x == 0L -> 0
//                x > 0L -> 1
//                else -> -1
//            }
//        }
//
//        // 计算从 l 到 r 区间的最小操作数
//        fun helper(l: Int, r: Int): Long {
//            var ret = 0L
//            var last = 0L
//            for (i in l..r) {
//                val x = abs(det[i])
//                if (x > last) ret += x - last
//                last = x
//            }
//            return ret
//        }
//
//        var ans = 0L
//        // 处理每一段的正数和负数
//        var j = 0
//        for (i in 0 until n) {
//            if (i == n - 1 || sgn(det[i + 1]) != sgn(det[j])) {
//                ans += helper(j, i)
//                j = i + 1
//            }
//        }
//
//        return ans
//    }
}