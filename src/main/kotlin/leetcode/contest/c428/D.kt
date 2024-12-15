package leetcode.contest.c428

import utils.print

fun main() {
    val s = SolutionD()
//    s.makeStringGood("aaabc").print()
//    s.makeStringGood("ruuu").print()
    // 3
    s.makeStringGood("gigigjjggjjgg").print()
}

class SolutionD {
    fun makeStringGood(s: String): Int {
        val str = s.toCharArray()
        val n = str.size
        val cnt = IntArray(26)
        for (c in str) {
            cnt[c - 'a']++
        }
        var mn = n
        var mx = 0
        for (c in cnt) {
            if (c > 0) {
                mn = minOf(mn, c)
                mx = maxOf(mx, c)
            }
        }
        if (mn == mx) {
            return 0
        }
        val dp = Array(26) { IntArray(2) }
        var ans = n - 1
        for (i in mn..mx) {
            dp[0][0] = cnt[0]
            dp[0][1] = if (cnt[0] <= i) i - cnt[0] else cnt[0] - i
            for (j in 1..25) {
                dp[j][0] = minOf(dp[j - 1][0], dp[j - 1][1]) + cnt[j]
                if (cnt[j] >= i) {
                    dp[j][1] = minOf(dp[j - 1][0], dp[j - 1][1]) + (cnt[j] - i)
                } else {
                    dp[j][1] = minOf(
                        dp[j - 1][0] + i - minOf(i, cnt[j] + cnt[j - 1]),
                        dp[j - 1][1] + i - minOf(i, cnt[j] + maxOf(0, cnt[j - 1] - i))
                    )
                }
            }
            ans = minOf(ans, dp[25][0], dp[25][1])
        }
        return ans
    }
}