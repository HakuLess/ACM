package leetcode.contest.b133

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
//    s.numberOfPermutations(3, "[[2,2],[0,0]]".toGrid()).print()
//    s.numberOfPermutations(3, "[[2,2],[1,1],[0,0]]".toGrid()).print()
    // 2
    s.numberOfPermutations(5, "[[0,0],[1,0],[2,0],[4,1]]".toGrid()).print()
}

// 符合 逆序对 数量的 排列数
class SolutionD {
    fun numberOfPermutations(n: Int, requirements: Array<IntArray>): Int {

        val mod = 1000000007L

        // 逆序对的最大值
        val m = requirements.maxOf { it[1] }

        // 将闲置条件提取
        val req = IntArray(n) { -1 }
        for (item in requirements) {
            req[item[0]] = item[1]
        }

        // 0..i 恰好 j 个逆序对
        val dp = Array(n) { LongArray(m + 1) }
        dp[0][0] = 1

        for (i in 1 until n) {
            // 逆序对范围限制
            // 有条件限制则只能为 req[i]
            val (l, r) = if (req[i] >= 0) {
                Pair(req[i], req[i])
            } else {
                Pair(0, m)
            }
            for (j in l..r) {
                // 额外补的逆序对数量
                for (det in 0..minOf(i, j)) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - det]) % mod
                }
            }
        }

        return dp[n - 1][req[n - 1]].toInt()
    }
}