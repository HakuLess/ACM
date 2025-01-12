package leetcode.contest.c432

import utils.print
import utils.toGrid
import kotlin.math.absoluteValue

fun main() {
    val s = SolutionB()
    s.maximumAmount("[[0,1,-1],[1,-2,3],[2,-3,4]]".toGrid()).print()
}

class SolutionB {
    fun maximumAmount(coins: Array<IntArray>): Int {
        val m = coins.size
        val n = coins[0].size

        val dp = Array(m) { Array(n) { IntArray(3) { Int.MIN_VALUE } } }

        dp[0][0][0] = coins[0][0]
        if (coins[0][0] < 0) {
            dp[0][0][1] = 0
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                for (k in 0..2) {
                    if (dp[i][j][k] == Int.MIN_VALUE) continue


                    if (j + 1 < n) {
                        if (coins[i][j + 1] >= 0) {
                            dp[i][j + 1][k] = maxOf(dp[i][j + 1][k], dp[i][j][k] + coins[i][j + 1])
                        } else {
                            val loss = coins[i][j + 1].absoluteValue
                            if (k < 2) {
                                dp[i][j + 1][k + 1] = maxOf(dp[i][j + 1][k + 1], dp[i][j][k])
                            }
                            dp[i][j + 1][k] = maxOf(dp[i][j + 1][k], dp[i][j][k] - loss)
                        }
                    }

                    if (i + 1 < m) {
                        if (coins[i + 1][j] >= 0) {
                            dp[i + 1][j][k] = maxOf(dp[i + 1][j][k], dp[i][j][k] + coins[i + 1][j])
                        } else {
                            val loss = coins[i + 1][j].absoluteValue
                            if (k < 2) {
                                dp[i + 1][j][k + 1] = maxOf(dp[i + 1][j][k + 1], dp[i][j][k])
                            }
                            dp[i + 1][j][k] = maxOf(dp[i + 1][j][k], dp[i][j][k] - loss)
                        }
                    }
                }
            }
        }

        return maxOf(dp[m - 1][n - 1][0], dp[m - 1][n - 1][1], dp[m - 1][n - 1][2])
    }
}