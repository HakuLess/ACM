package leetcode.normal

import utils.print

fun main() {
    val s = Solution600()
    s.findIntegers(10245).print()
}

// 转化为字典树，可免去计算重复问题，相同高度问题相同
class Solution600 {
    fun findIntegers(num: Int): Int {
        val dp = IntArray(31)
        dp[0] = 1
        dp[1] = 1
        for (i in 2..30) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }

        var pre = 0
        var res = 0
        for (i in 29 downTo 0) {
            val cur = 1 shl i
            if (num and cur != 0) {
                res += dp[i + 1]
                if (pre == 1) break
                pre = 1
            } else pre = 0

            if (i == 0) ++res
        }
        return res
    }
}