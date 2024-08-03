package leetcode.contest.b136

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionA()
    s.winningPlayerCount(4, "[[0,0],[1,0],[1,0],[2,1],[2,1],[2,0]]".toGrid()).print()
}

class SolutionA {
    fun winningPlayerCount(n: Int, pick: Array<IntArray>): Int {
        val playerBalls = Array(n) { mutableMapOf<Int, Int>() }

        for (p in pick) {
            val player = p[0]
            val color = p[1]
            playerBalls[player][color] = playerBalls[player].getOrDefault(color, 0) + 1
        }

        var winnerCount = 0
        for (i in 0 until n) {
            val balls = playerBalls[i]
            for ((_, count) in balls) {
                if (count > i) {
                    winnerCount++
                    break
                }
            }
        }

        return winnerCount
    }
}