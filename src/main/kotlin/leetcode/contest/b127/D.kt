package leetcode.contest.b127

import utils.C
import utils.SortedList
import utils.originC
import utils.print
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.abs

fun main() {
    val s = SolutionD()
    // 4
    s.sumOfPowers(intArrayOf(1, 2, 3, 4), 3).print()
    // 0
    s.sumOfPowers(intArrayOf(2, 2), 2).print()
    // 10
    s.sumOfPowers(intArrayOf(4, 3, -1), 2).print()
    // 4
    s.sumOfPowers(intArrayOf(-16, -6, -12, -2), 4).print()
}

class SolutionD {
    fun sumOfPowers(nums: IntArray, k: Int): Int {
        nums.sort()
        val n = nums.size
        val mod = 1000000007L

        // 比 lowerDiff 大的取法
        fun f(nums: List<Int>, lowerDiff: Int): LongArray {
            val n = nums.size
            val dp = Array(n) { LongArray(k) }
            dp[0][1] = 1
            for (i in 1 until n) {
                for (j in 0 until i) {
                    if (nums[i] - nums[j] < lowerDiff) break
                    for (v in 0 until k - 1) {
                        dp[i][v + 1] += dp[j][v]
                        dp[i][v + 1] %= mod
                    }
                }
            }
            val ans = LongArray(k)
            for (i in 0 until n) {
                for (j in 0 until k) {
                    ans[j] += dp[i][j]
                }
            }
            return ans
        }

        var ans = 0L
        for (i in 0 until n) {
            for (j in 0 until i) {
                // v的贡献度
                val v = nums[i] - nums[j]

                // 左侧取法
                val vs1 = ArrayList<Int>()
                for (idx in j downTo 0) {
                    vs1.add(nums[j] - nums[idx])
                }
                val dp1 = f(vs1, v + 1)

                // 右侧取法
                val vs2 = ArrayList<Int>()
                for (idx in i until n) {
                    vs2.add(nums[idx] - nums[i])
                }
                val dp2 = f(vs2, v)

                for (x in 1 until k) {
                    ans += dp1[x] * dp2[k - x] * v % mod
                    ans %= mod
                }
            }
        }
        return ans.toInt()
    }
}