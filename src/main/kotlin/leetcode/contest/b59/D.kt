package leetcode.contest.b59

import utils.print
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {
    val s = Solution5837()
    s.numberOfCombinations("327").print()
    s.numberOfCombinations("9999999999999").print()
    val str = StringBuilder()
    repeat(3500) {
        str.append('9')
    }
//    repeat(1500) {
//        str.append('9')
//    }
    measureTime {
        s.numberOfCombinations(str.toString()).print()
    }.also {
        println(it)
    }

    // 8 49 69
    // 84 969
    // 84969
    // 8 4969
//    s.numberOfCombinations("84969").print()
}

class Solution5837 {
    fun numberOfCombinations(num: String): Int {
        if (num.startsWith('0')) return 0

        val n = num.length
        val c = Array<IntArray>(n) { IntArray(n) }
        // c[i][j] i开始 j开始 的字符串，能满足 前者小于后者的 最大长度
        for (len in 1..n) {
            for (i in n - 1 - len downTo 0) {
                if (num[i] < num[i + len])
                // 第一位就小于，长度可延展至字符串尾
                    c[i][i + len] = n - i - len
                else if (num[i] == num[i + len]) {
                    if (i + len == n - 1)
                    // 相等，若是最后位置则为1
                        c[i][i + len] = 1
                    else
                    // 否则，可延展至下一位判断，总长度+1
                        c[i][i + len] = c[i + 1][i + len + 1] + 1
                }
                // 第一位大于，默认值0
            }
        }

        // 快速判断 l开头 和 r开头 并且长度都为len的字符串，前者是否小于后者
        fun cmp(l: Int, r: Int, len: Int): Boolean {
            if (l < 0)
                return false
            return c[l][r] >= len
        }

        val mod = 1000000007L
        val seen = LongArray((n + 1) * (n + 1)) { -1 }

        // 前j个字符进行划分，且其中最后一个字符串startIndex为i
        // [0,i-1] [i,j]分割数
        fun dfs(i: Int, j: Int): Long {
            // 最后一个字符串有前导0，无方案
            if (num[i] == '0') return 0L
            // 完整分割成1个字符串
            if (i == 0) return 1L
            val key = i * (n + 1) + j
            if (seen[key] != -1L) return seen[key]

            var ans = 0L
            for (k in i - 1 downTo 0) {
                // k作为上一次分割的startIndex
                // i作为本次分割的startIndex

                // 1. 当前分割长度 大于 上次分割长度，则数字一定更大
                // 2. 长度相等情况，判断是否本次字符串更大
                if (j - i + 1 > i - k
                    || (j - i + 1 == i - k && cmp(k, i, i - k))
                ) {
                    ans = (ans + dfs(k, i - 1)) % mod
                } else {
                    break
                }
            }
            return ans.also {
                seen[key] = it
            }
        }

        var ans = 0L
        for (i in 0 until n) {
            // 完全拆分，最后一个字符串的startIndex完整遍历
            ans = (ans + dfs(i, n - 1)) % mod
        }
        return (ans % mod).toInt()

        // Copy DP 方法
//        fun dp(): Int {
//
//            val mod = 1000000007
//            // dp[i][j] lastIndex位置到i，最后一个数字长度为j
//            val dp = Array<IntArray>(n + 1) { IntArray(n + 1) }
//            dp[1] = IntArray(n + 1) { 1 }
//            dp[1][0] = 0
//
//            for (i in 2..n) {
//                dp[i][i] = 1
//                for (j in 1 until i) {
//                    // 当前数字是否以'0'开头
//                    if (num[i - j] == '0')
//                        continue
//                    if (cmp(i - 2 * j, i - j, j))
//                        dp[i][j] = (dp[i][j] + dp[i - j][j]) % mod
//                    else
//                        dp[i][j] = (dp[i][j] + dp[i - j][j - 1]) % mod
//                }
//                for (j in 1..n)
//                    dp[i][j] = (dp[i][j] + dp[i][j - 1]) % mod
//            }
//            return dp[n][n]
//        }
//        return dp()
    }
}