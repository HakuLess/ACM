package leetcode.contest.c286

import utils.print

fun main() {
    val s = SolutionD()
//    s.maxValueOfCoins(listOf(listOf(1, 100, 3), listOf(7, 8, 9)), 2).print()
    // 494
    s.maxValueOfCoins(
        listOf(
            listOf(37, 88),
            listOf(51, 64, 65, 20, 95, 30, 26),
            listOf(9, 62, 20),
            listOf(44)
        ), 9
    ).print()
}

class SolutionD {
    fun maxValueOfCoins(piles: List<List<Int>>, k: Int): Int {
        var dp = IntArray(k + 1)
        var dpNew = IntArray(k + 1)
        for (i in piles.indices) {
            var cur = 0
            for (j in piles[i].indices) {
                cur += piles[i][j]
                for (l in dp.indices) {
                    if (l + j + 1 in dp.indices) {
                        dpNew[l + j + 1] = maxOf(dpNew[l + j + 1], dp[l] + cur)
                        dpNew[l + j + 1] = maxOf(dpNew[l + j + 1], dp[l + j + 1])
                    }
                }
            }
            dp = dpNew
            dpNew = IntArray(k + 1)
        }
        return dp.last()
    }
}