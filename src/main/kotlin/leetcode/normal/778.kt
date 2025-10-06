package leetcode.normal

import utils.dir4
import java.util.PriorityQueue
import kotlin.io.path.Path

class Solution778 {
    fun swimInWater(grid: Array<IntArray>): Int {

        val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.first })
        pq.offer(Triple(grid[0][0], 0, 0))

        val seen = HashSet<Pair<Int, Int>>()
        while (pq.isNotEmpty()) {
            val (time, x, y) = pq.poll()
            if (x == grid.lastIndex && y == grid[0].lastIndex) {
                return time
            }

            seen.add(Pair(x, y))
            dir4.forEach {
                val nextX = x + it[0]
                val nextY = y + it[1]
                if (nextX !in grid.indices || nextY !in grid[0].indices) {
                    return@forEach
                }
                if (Pair(nextX, nextY) in seen) {
                    return@forEach
                }
                val nextTime = maxOf(time, grid[nextX][nextY])
                pq.offer(Triple(nextTime, nextX, nextY))
            }
        }
        return -1
    }
}