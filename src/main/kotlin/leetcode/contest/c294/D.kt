package leetcode.contest.c294

import utils.minBound
import utils.preSumArray
import utils.print
import utils.toBigInteger
import java.math.BigInteger

fun main() {
    val s = SolutionD()
    s.totalStrength(intArrayOf(1, 3, 1, 2)).print()
//    s.totalStrength(intArrayOf(5, 4, 6)).print()
}

// 公式推导...
class SolutionD {
    fun totalStrength(strength: IntArray): Int {
        val mod = 1000000007L
        val preSum = strength.preSumArray(false).preSumArray(true)
        val minBound = strength.minBound()
        var ans = 0L
        for (i in strength.indices) {
            val l = minBound[i][0]
            val r = minBound[i][1]
            val lSum = (preSum[i] - preSum[maxOf(l, 0)]) % mod
            val rSum = (preSum[r] - preSum[i]) % mod
            val lc = i - l
            val rc = r - i
            ans += strength[i].toLong() * (((rSum * lc - lSum * rc) + mod) % mod)
            ans %= mod
        }
        return ans.toInt()
    }
}