package leetcode.contest.c295

import utils.dir4
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    s.minimumObstacles("[[0,1,1],[0,1,1],[1,1,0]]".toGrid()).print()
}

class SolutionD {
    fun minimumObstacles(grid: Array<IntArray>): Int {
        val pq = PriorityQueue<IntArray>(compareBy { it[2] })
        pq.offer(intArrayOf(0, 0, grid[0][0]))
        val seen = HashSet<String>()
        seen.add(intArrayOf(0, 0).joinToString())
        while (pq.isNotEmpty()) {
            val item = pq.poll()
            if (item[0] == grid.lastIndex && item[1] == grid[0].lastIndex) {
                return item[2]
            }
            dir4.forEach {
                val nextX = item[0] + it[0]
                val nextY = item[1] + it[1]
                if (nextX in grid.indices && nextY in grid[0].indices) {
                    val next = intArrayOf(nextX, nextY)
                    if (next.joinToString() !in seen) {
                        pq.offer(intArrayOf(nextX, nextY, item[2] + grid[nextX][nextY]))
                        seen.add(next.joinToString())
                    }
                }
            }
        }
        return -1
    }
}