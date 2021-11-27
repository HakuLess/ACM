package leetcode.contest.b66

import utils.dir4
import utils.print
import java.util.*
import kotlin.collections.HashMap

fun main() {
    val s = SolutionC()
    s.minCost(intArrayOf(1, 0), intArrayOf(2, 3), intArrayOf(5, 4, 3), intArrayOf(8, 2, 6, 7)).print()
    s.minCost(intArrayOf(0, 0), intArrayOf(1, 2), intArrayOf(10, 2, 5), intArrayOf(10, 6, 1)).print()
}

class SolutionC {
    fun minCost(startPos: IntArray, homePos: IntArray, rowCosts: IntArray, colCosts: IntArray): Int {
        val pq = PriorityQueue<Int>()
        val boundX = intArrayOf(minOf(startPos[0], homePos[0]), maxOf(startPos[0], homePos[0]))
        val boundY = intArrayOf(minOf(startPos[1], homePos[1]), maxOf(startPos[1], homePos[1]))
        for (i in boundX[0]..boundX[1]) {
            if (i == startPos[0]) continue
            pq.offer(rowCosts[i])
        }
        for (i in boundY[0]..boundY[1]) {
            if (i == startPos[1]) continue
            pq.offer(colCosts[i])
        }

//        pq.joinToString().print()
        return pq.sum()
    }
}