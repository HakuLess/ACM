package leetcode.contest.c435

import utils.gcd
import utils.print
import java.util.*


fun main() {
    val s = SolutionC()
    // 1
    s.minimumIncrements(intArrayOf(1, 2, 3), intArrayOf(4)).print()
//    // 2
    s.minimumIncrements(intArrayOf(8, 4), intArrayOf(10, 5)).print()
//
//    // 3
    s.minimumIncrements(intArrayOf(8, 10, 9), intArrayOf(10, 6, 6)).print()

    // 8
    s.minimumIncrements(intArrayOf(10, 4, 5, 10), intArrayOf(6, 8, 7, 3)).print()
}

class SolutionC {
    fun minimumIncrements(nums: IntArray, target: IntArray): Int {

        val max = 1 shl target.size
        // 状态压缩，计算target已经被拿走的最小情况。
        val lcm = LongArray(max)
        for (i in 1 until max) {
            var cur: Long = -1
            for (j in target.indices) {
                if (i shr j and 1 == 1) {
                    cur = if (cur == -1L) {
                        target[j].toLong()
                    } else {
                        val tmp: Long = gcd(cur, target[j].toLong())
                        cur / tmp * target[j]
                    }
                }
            }
            lcm[i] = cur
        }

//        lcm.print()

        var dp = LongArray(max) { Long.MAX_VALUE }
        dp[0] = 0
        for (n in nums) {
            val nextDp = LongArray(max) { Long.MAX_VALUE }
            nextDp[0] = 0
            // 符合当前target取法，已经完成的最小情况
            for (i in 1 until max) {
                nextDp[i] = dp[i]
                for (j in 0 until max) {
                    if (i != j && dp[j] != Long.MAX_VALUE && i or j == i) {
                        val l = lcm[i xor j]
                        if (n % l == 0L) {
                            nextDp[i] = minOf(nextDp[i], dp[j])
                        } else {
                            nextDp[i] = minOf(nextDp[i], dp[j] + (n / l + 1) * l - n)
                        }
                    }
                }
            }
            dp = nextDp
        }
        return dp[max - 1].toInt()
    }
}