package leetcode.normal

// DP
// 解码A..Z
class Solution639 {
    fun numDecodings(s: String): Int {
        val mod = 1000000007L
        // 上一位以 3..9、1、2结尾的（0结尾无法组合出下一个数字，因此不需要）
        val dp = LongArray(3)
        // 最后两位一起组合成一个数字的
        var half: Long
        var sum = 1L
        for (c in s) {
            if (c == '*') {
                // 最后两位整体
                half = dp[1] * 9 + dp[2] * 6
                // 最后一位单独
                dp[0] = sum * 7
                dp[1] = sum
                dp[2] = sum
            } else {
                val x = c - '0'
                // 当前值小于6，才可以补2的后面
                half = (if (x < 7) dp[2] else 0) + dp[1]
                // 重新赋值DP
                dp.fill(0L)
                // x为1~2时，dp[x]=sum
                // x为3~9时，dp[0]=sum, 为0时不变
                if (x in 1..2) {
                    dp[x] = sum
                } else if (x in 3..9) {
                    dp[0] = sum
                }
            }
            // 计算出当前总数
            sum = (dp[0] + dp[1] + dp[2] + half) % mod
        }
        return sum.toInt()
    }
}