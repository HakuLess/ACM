package leetcode.contest.c442

import utils.preSumArray
import utils.print

fun main() {
    val s = SolutionC()
    // 110
    s.minTime(intArrayOf(1, 5, 2, 4), intArrayOf(5, 1, 4, 2)).print()
    // 21
    s.minTime(intArrayOf(1, 2, 3, 4), intArrayOf(1, 2)).print()
}

class SolutionC {
    fun minTime(skill: IntArray, mana: IntArray): Long {
        val n = skill.size
        val m = mana.size

        val preSum = skill.preSumArray(true)

        var dp = LongArray(n)
        // 第一个药水，正常按顺序处理
        dp[0] = 1L * skill[0] * mana[0]
        for (i in 1 until n) {
            dp[i] = dp[i - 1] + skill[i] * mana[0]
        }

        // 需要处理m个药水
        for (j in 1 until m) {
            var offset = Long.MIN_VALUE
            // 被前面卡着
            for (i in 0 until n) {
                // offset + preSum[i] * mana[j] >= dp[i]
                // 工作流转到当前时，时间 >= 上一次完成时间
                val c = dp[i] - preSum[i] * mana[j]
                offset = maxOf(offset, c)
            }

//            offset.print()

            val newDp = LongArray(n)
            newDp[0] = offset + skill[0] * mana[j]
            for (i in 1 until n) {
//                newDp[i] = newDp[i - 1] + skill[i] * mana[j]
                newDp[i] = maxOf(newDp[i - 1], dp[i]) + skill[i] * mana[j]
            }
            dp = newDp
        }
        return dp[n - 1]
    }
}