package leetcode.contest.c362

import utils.print

fun main() {
    val s = SolutionD()
//    s.numberOfWays("abcd", "cdab", 2).print()
//    s.numberOfWays("ababab", "ababab", 1).print()
//    s.numberOfWays("aaaa", "aaaa", 8).print()
//    s.numberOfWays("aaaa", "aaaa", 4).print()
    // 322134019
    s.numberOfWays("wkldv", "ldvwk", 854972569843185).print()
}

class SolutionD {
    fun numberOfWays(s: String, t: String, k: Long): Int {
        val n = s.length
        val mod = 1000000007L
        val l = ArrayList<Int>()
        for (i in t.indices) {
            if (s == t.substring(i, t.length) + t.substring(0, i)) {
                l.add(i)
            }
        }
        if (l.isEmpty()) return 0
//        l.joinToString().print()

        var dp = LongArray(n)
        dp[0] = 1
//        repeat((k % mod).toInt()) {
        repeat(Int.MAX_VALUE) {
            val nextDp = LongArray(n)
            for (i in dp.indices) {
                for (j in 1..n - 1) {
                    nextDp[(i + j) % n] = dp[i] + nextDp[(i + j) % n]
                    nextDp[(i + j) % n] %= mod
                }
            }
            dp = nextDp
            dp.print()

            var ans = 0L
            l.forEach {
                ans += dp[it]
                ans %= mod
            }
            if (ans.toInt() == 322134019) {
                println("out $it")
                return it
            }
        }
//         todo DP
        var ans = 0L
        l.forEach {
            ans += dp[it]
            ans %= mod
        }

        return (ans % mod).toInt()
    }
}