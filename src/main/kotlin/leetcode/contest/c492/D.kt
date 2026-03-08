package leetcode.contest.c492

import utils.print

fun main() {
    val s = SolutionD()
    // 6
    s.minCost("1010", 2, 1).print()
    // 12
    s.minCost("1010", 3, 10).print()
    // 2
    s.minCost("00", 1, 2).print()
    // 66
    s.minCost("010", 22, 8).print()
}

class SolutionD {
    fun minCost(s: String, encCost: Int, flatCost: Int): Long {

        val n = s.length

        val pre = IntArray(n + 1)
        for (i in 0 until n) {
            pre[i + 1] = pre[i] + (s[i] - '0')
        }

        fun cost(l: Int, len: Int): Long {
            val x = pre[l + len] - pre[l]
            return if (x == 0) flatCost.toLong()
            else 1L * len * x * encCost
        }

        val dp = HashMap<Pair<Int, Int>, Long>()

        fun solve(l: Int, len: Int): Long {
            val key = l to len
            if (key in dp) return dp[key]!!

            var ans = cost(l, len)

            if (len % 2 == 0) {
                val half = len / 2
                ans = minOf(ans, solve(l, half) + solve(l + half, half))
            }

            dp[key] = ans
            return ans
        }

        return solve(0, n)
    }
}