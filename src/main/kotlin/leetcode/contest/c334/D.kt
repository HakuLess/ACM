package leetcode.contest.c334

import utils.dir4
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    s.minimumTime("[[0,1,3,2],[5,1,2,5],[4,3,8,6]]".toGrid()).print()
}

class SolutionD {
    fun minimumTime(grid: Array<IntArray>): Int {
        if (grid[0][1] > 1 && grid[1][0] > 1) return -1

        val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
        pq.offer(Triple(0, 0, 0))
        val seen = HashSet<Pair<Int, Int>>()
        while (pq.isNotEmpty()) {
            val item = pq.poll()
            val p = Pair(item.first, item.second)
            if (p in seen) continue
            seen.add(p)
            println("enter $item")
            if (item.first == grid.lastIndex && item.second == grid[0].lastIndex) {
                return item.third
            }
            dir4.forEach {
                val nextX = item.first + it[0]
                val nextY = item.second + it[1]
                if (nextX in grid.indices
                    && nextY in grid[0].indices
                ) {
                    val v = grid[nextX][nextY]
                    val next = if (v <= item.third + 1) {
                        Triple(nextX, nextY, item.third + 1)
                    } else {
                        Triple(nextX, nextY, v + if ((v - item.third) % 2 == 0) 1 else 0)
                    }
                    if (Pair(nextX, nextY) !in seen) {
                        pq.offer(next)
                    }
                }
            }
        }
        return -1
    }
}