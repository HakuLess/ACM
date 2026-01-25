package leetcode.contest.c486

import utils.print

fun main() {
    val s = SolutionD()
    // 9
    s.nthSmallest(4, 2).print()
    // 4
    s.nthSmallest(3, 1).print()
}

class SolutionD {

    // 组合数表：C[n][k]
    private val C = Array(65) { LongArray(65) }

    // 预先计算组合数，快速计算
    init {
        for (i in 0..64) {
            C[i][0] = 1
            for (j in 1..i) {
                if (j == i) C[i][j] = 1
                else {
                    val v = C[i - 1][j - 1] + C[i - 1][j]
                    C[i][j] = if (v < 0) Long.MAX_VALUE else v
                }
            }
        }
    }

    fun nthSmallest(n: Long, k: Int): Long {

        var leftN = n

        // 最高位1 已经可以枚举出足够量的数字时，不用再考虑更大的数
        var highestBit = 0
        while (true) {
            val count = if (highestBit >= k - 1) C[highestBit][k - 1] else 0L
            if (count >= leftN) break
            leftN -= count
            highestBit++
        }

        var ans = 1L shl highestBit
        var leftOnes = k - 1

        // 同之前逻辑，最高位逐步下探，侵蚀剩余的 index
        for (bit in highestBit - 1 downTo 0) {
            if (leftOnes == 0) break

            val cnt = if (bit >= leftOnes) C[bit][leftOnes] else 0L

            if (leftN > cnt) {
                leftN -= cnt
                ans = ans or (1L shl bit)
                leftOnes--
            }
        }

        return ans
    }
}