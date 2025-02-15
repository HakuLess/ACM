package leetcode.contest.c436

import utils.print


fun main() {
    val s = SolutionC()
    s.countSubstrings("12936").print()
    s.countSubstrings("1010101010").print()
}

class SolutionC {
    fun countSubstrings(s: String): Long {
        val data = s.map { it - '0' }
        val n = data.size
        var ans = 0L
        for (x in 1..9) {
            var dp = LongArray(10)
            for (i in 0 until n) {
                val next = LongArray(10)
                next[data[i] % x]++
                for (j in 0..9) {
                    next[(j * 10 + data[i]) % x] += dp[j]
                }
                dp = next
                if (data[i] == x) {
                    ans += dp[0]
                }
            }
        }
        return ans
    }
}