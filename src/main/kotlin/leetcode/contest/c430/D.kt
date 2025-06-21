package leetcode.contest.c430

import utils.print
import utils.quickPower

fun main() {
    val s = SolutionD()
    s.countGoodArrays(5, 2, 0).print()
}

class SolutionD {
    fun countGoodArrays(n: Int, m: Int, k: Int): Int {
        val mod = 1_000_000_007L
        if (k > n - 1) {
            return 0
        }

        // 计算组合数 C(n-1, k)
        var combinations = 1L
        for (i in 0 until k) {
            combinations = (combinations * (n - 1 - i) % mod * quickPower((i + 1).toLong(), mod - 2, mod) % mod) % mod
        }

        // 计算结果
        var result = combinations
        result = (result * m) % mod

        val power = quickPower(base = (m - 1).toLong(), pow = (n - 1 - k).toLong(), m = mod)
        result = (result * power) % mod

        return result.toInt()
    }
}