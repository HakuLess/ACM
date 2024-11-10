package leetcode.contest.c423

class SolutionD {
    fun countKReducibleNumbers(s: String, k: Int): Int {
        val mod = 1_000_000_007L
        val pre = IntArray(1001)

        for (i in 2 until 1001) {
            pre[i] = pre[i.countOneBits()] + 1
        }

        val nums = s.map { it - '0' }
        val n = nums.size
        val dp = LongArray(n + 1)

        var cur = 0
        for (i in nums.indices) {
            for (j in i - 1 downTo 0) {
                dp[j + 1] += dp[j]
                dp[j + 1] %= mod
            }
            if (nums[i] != 0) {
                dp[cur] += 1L
                dp[cur] %= mod
            }
            cur += nums[i]
        }

        var ans = 0L
        for (i in 0..n) {
            if (pre[i] < k) {
                ans += dp[i]
                ans %= mod
            }
        }

        return ((ans - 1 + mod) % mod).toInt()
    }
}