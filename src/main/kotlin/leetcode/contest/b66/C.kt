package leetcode.contest.b66

import utils.print
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionC()
    s.minCost(intArrayOf(1, 0), intArrayOf(2, 3), intArrayOf(5, 4, 3), intArrayOf(8, 2, 6, 7)).print()
    s.minCost(intArrayOf(0, 0), intArrayOf(1, 2), intArrayOf(10, 2, 5), intArrayOf(10, 6, 1)).print()
}

class SolutionC {
    fun minCost(startPos: IntArray, homePos: IntArray, rowCosts: IntArray, colCosts: IntArray): Int {
        val arr = ArrayList<Int>()
        val boundX = intArrayOf(minOf(startPos[0], homePos[0]), maxOf(startPos[0], homePos[0]))
        val boundY = intArrayOf(minOf(startPos[1], homePos[1]), maxOf(startPos[1], homePos[1]))
        for (i in boundX[0]..boundX[1]) {
            if (i == startPos[0]) continue
            arr.add(rowCosts[i])
        }
        for (i in boundY[0]..boundY[1]) {
            if (i == startPos[1]) continue
            arr.add(colCosts[i])
        }
        return arr.sum()
    }
}