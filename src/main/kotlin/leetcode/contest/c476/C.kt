package leetcode.contest.c476

import utils.print

fun main() {
    val s = SolutionC()
    s.countDistinct(10).print()
    s.countDistinct(2341).print()
}

class SolutionC {
    fun countDistinct(n: Long): Long {

        val digits = n.toString()
        val len = digits.length

        // dp[pos][tight][started]
        val memo = Array(len) { Array(2) { LongArray(2) { -1L } } }

        fun dfs(pos: Int, tight: Int, started: Int): Long {
            if (pos == len) {
                return if (started == 1) 1 else 0
            }
            if (memo[pos][tight][started] != -1L) {
                return memo[pos][tight][started]
            }

            val limit = if (tight == 1) digits[pos] - '0' else 9
            var ans = 0L

            for (d in 0..limit) {
                // skip d == 0
                if (d == 0 && started == 1) {
                    continue
                }

                val nextTight = if (tight == 1 && d == limit) 1 else 0
                val nextStarted = if (started == 1 || d != 0) 1 else 0

                ans += dfs(pos + 1, nextTight, nextStarted)
            }

            memo[pos][tight][started] = ans
            return ans
        }

        return dfs(0, 1, 0)
    }
}