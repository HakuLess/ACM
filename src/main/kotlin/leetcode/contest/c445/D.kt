package leetcode.contest.c445

import utils.print
import java.math.BigInteger

fun main() {
    val s = SolutionD()
    s.countNumbers("23", "28", 8).print()
}

class SolutionD {
    fun countNumbers(l: String, r: String, b: Int): Int {

        val mod = 1000000007L

        val bigL = BigInteger(l)
        val bigR = BigInteger(r)

        fun helper(num: BigInteger, b: Int): Long {
            if (num < BigInteger.ZERO) return 0L

            // b 进制 数字
            val digits = num.toString(b).map { it - '0' }
            val n = digits.size

            val seen = HashMap<String, Long>()

            fun dfs(pos: Int, last: Int, started: Boolean, tight: Boolean): Long {
                if (pos == n) return 1L

                val key = "$pos, $last, $started, $tight"
                if (key in seen.keys) return seen[key]!!

                val limit = if (tight) digits[pos] else (b - 1)
                var ways = 0L

                for (d in 0..limit) {
                    if (started && d < last) continue

                    val nextStarted = if (started) true else (d != 0)
                    val nextLast = if (started || d != 0) d else last

                    val nextTight = tight && (d == limit)
                    ways = (ways + dfs(pos + 1, nextLast, nextStarted, nextTight)) % mod
                }
                return ways.also {
                    seen[key] = it % mod
                }
            }

            return dfs(0, 0, false, true)
        }

        val rightCnt = helper(bigR, b)
        val leftCnt = helper(bigL - BigInteger.ONE, b)
        val ans = (rightCnt - leftCnt + mod) % mod
        return ans.toInt()
    }
}