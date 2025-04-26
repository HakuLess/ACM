package leetcode.contest.b155

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.maxProfit(3, "[[0,1],[0,2]]".toGrid(), intArrayOf(1, 6, 3)).print()
    // 81906
    s.maxProfit(2, "[[0,1]]".toGrid(), intArrayOf(37884, 22011)).print()
}

class SolutionD {
    fun maxProfit(n: Int, edges: Array<IntArray>, score: IntArray): Int {

        // 处理掩码，判定是否前序节点已完成
        val preMask = LongArray(n) { 0L }
        for ((u, v) in edges) {
            preMask[v] = preMask[v] or (1L shl u)
        }

        // 完整遍历情况
        val last = (1 shl n) - 1

        val dp = IntArray(1 shl n) { Int.MIN_VALUE }
        dp[0] = 0

        for (mask in 0..last) {
            val cur = dp[mask]
            if (cur == Int.MIN_VALUE) continue

            // 记录已经选中多少点，辅助乘积计算
            val pos = mask.countOneBits() + 1

            // 判定当前节点i,是否可以选择为下一个
            for (i in 0 until n) {
                // 当前i已被选择过
                if (mask and (1 shl i) != 0) continue
                // 前置节点均已选择完成，当前节点可被选择
                if ((mask.toLong() and preMask[i]) != preMask[i]) continue

                val nextMask = mask or (1 shl i)
                dp[nextMask] = maxOf(dp[nextMask], cur + score[i] * pos)
            }
        }

        return dp[last]
    }
}