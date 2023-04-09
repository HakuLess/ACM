package leetcode.contest.c340

import java.util.*

class SolutionD {
    fun minimumVisitedCells(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        if (m == 1 && n == 1) return 1

        val dist = Array(m) { IntArray(n) { -1 } }
        dist[0][0] = 0

        val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
        queue.offer(Pair(0, 0))
        var step = 0
        while (queue.isNotEmpty()) {
            step++
            val size = queue.size
            for (i in 0 until size) {
                val (x, y) = queue.poll()
                val v = grid[x][y]
                for (nextX in x + v downTo x + 1) {
                    if (nextX in grid.indices) {
                        if (dist[nextX][y] == -1) {
                            queue.offer(Pair(nextX, y))
                            dist[nextX][y] = step + 1

                            if (dist[m - 1][n - 1] != -1) return dist[m - 1][n - 1]
                        }
                    }
                }
                for (nextY in y + v downTo y + 1) {
                    if (nextY in grid[0].indices) {
                        if (dist[x][nextY] == -1) {
                            queue.offer(Pair(x, nextY))
                            dist[x][nextY] = step + 1

                            if (dist[m - 1][n - 1] != -1) return dist[m - 1][n - 1]
                        }
                    }
                }
            }
        }

        return dist[m - 1][n - 1]
    }
}