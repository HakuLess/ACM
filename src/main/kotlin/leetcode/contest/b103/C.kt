package leetcode.contest.b103

import utils.dir4
import java.util.*

class SolutionC {
    fun findMaxFish(grid: Array<IntArray>): Int {

        fun bfs(x: Int, y: Int): Int {
            var ans = 0
            val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
            queue.offer(Pair(x, y))
            while (queue.isNotEmpty()) {
                val size = queue.size
                for (i in 0 until size) {
                    val (x, y) = queue.poll()
                    ans += grid[x][y]
                    grid[x][y] = 0
                    dir4.forEach {
                        val nextX = x + it[0]
                        val nextY = y + it[1]
                        if (nextX in grid.indices && nextY in grid[0].indices && grid[nextX][nextY] > 0) {
                            queue.offer(Pair(nextX, nextY))
                        }
                    }
                }
            }
            return ans
        }

        var res = 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] > 0) {
                    res = maxOf(res, bfs(i, j))
                }
            }
        }
        return res
    }
}