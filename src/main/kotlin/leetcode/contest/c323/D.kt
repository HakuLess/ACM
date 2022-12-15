package leetcode.contest.c323

import utils.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    s.maxPoints("[[1,2,3],[2,5,7],[3,5,1]]".toGrid(), intArrayOf(5, 6, 2)).print()
}

class SolutionD {
    fun maxPoints(grid: Array<IntArray>, queries: IntArray): IntArray {
        val queue: PriorityQueue<Triple<Int, Int, Int>> = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
        queue.offer(Triple(0, 0, grid[0][0]))
        grid[0][0] = 0

        val sorted = queries.mapIndexed { index, i -> Pair(index, i) }.sortedBy { it.second }
        val ans = IntArray(queries.size)
        var c = 0
        sorted.forEach {
            while (queue.isNotEmpty() && queue.peek().third < it.second) {
                val item = queue.poll()
                c++
                dir4.forEach {
                    val nextX = item.first + it[0]
                    val nextY = item.second + it[1]
                    if (nextX in grid.indices && nextY in grid[0].indices && grid[nextX][nextY] != 0) {
                        val nextV = maxOf(item.third, grid[nextX][nextY])
                        queue.offer(Triple(nextX, nextY, nextV))
                        grid[nextX][nextY] = 0
                    }
                }
            }
            ans[it.first] = c
        }
        return ans
    }
}