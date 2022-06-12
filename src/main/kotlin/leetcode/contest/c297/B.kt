package leetcode.contest.c297

import utils.print
import utils.toGrid
import kotlin.math.cos

fun main() {
    val s = SolutionB()
    s.minPathCost("[[5,3],[4,0],[2,1]]".toGrid(), "[[9,8],[1,5],[10,12],[18,6],[2,4],[14,3]]".toGrid()).print()
}

class SolutionB {
    fun minPathCost(grid: Array<IntArray>, moveCost: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size
        val cost = Array<IntArray>(n) { IntArray(m) { Int.MAX_VALUE / 2 } }
        for (i in 0 until m) {
            cost[0][i] = grid[0][i]
        }
        cost.print()
        for (i in 0 until n - 1) {
            for (j in grid[0].indices) {
                val item = grid[i][j]
                for (k in 0 until m) {
//                    println("$i $j with ${moveCost[item][k]}")
                    cost[i + 1][k] = minOf(cost[i + 1][k], cost[i][j] + moveCost[item][k] + grid[i + 1][k])
                }
            }
        }
//        return cost.last().min()!!
        return cost.last().minOrNull()!!
    }
}