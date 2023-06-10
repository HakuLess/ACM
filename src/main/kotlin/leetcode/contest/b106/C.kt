package leetcode.contest.b106

import utils.preSumArray
import utils.print

fun main() {
    val s = SolutionC()
    s.sumDistance(intArrayOf(-2, 0, 2), "RLL", 3).print()
    s.sumDistance(intArrayOf(1, 0), "RL", 2).print()
}

class SolutionC {
    fun sumDistance(nums: IntArray, s: String, d: Int): Int {
        val mod = 1000000007L
        val c = LongArray(nums.size)
        for (i in s.indices) {
            c[i] = nums[i] + (if (s[i] == 'R') d.toLong() else -d.toLong())
        }
        c.sort()

        val preSum = c.preSumArray(true)
        var ans = 0L
        for (i in c.indices) {
            ans += c[i] * i - preSum[i]
            ans %= mod
        }
        return (ans % mod).toInt()
    }
}