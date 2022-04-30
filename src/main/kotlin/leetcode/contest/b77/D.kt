package leetcode.contest.b77

import utils.biMax
import utils.dir4
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    s.maximumMinutes("[[0,2,0,0,1],[0,2,0,2,2],[0,2,0,0,0],[0,0,2,2,0],[0,0,0,0,0]]".toGrid()).print()
}

class SolutionD {
    fun maximumMinutes(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size

        val g = Array<IntArray>(n) { IntArray(m) { -2 } }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (grid[i][j] == 2) {
                    g[i][j] = -1
                }
            }
        }

        val queue = LinkedList<IntArray>()
        val seen = HashSet<String>()
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (grid[i][j] == 2) {
                    g[i][j] = -1
                }
                if (grid[i][j] == 1) {
                    queue.offer(intArrayOf(i, j))
                    seen.add("$i,$j")
                }
            }
        }
        var step = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            step++
            for (i in 0 until size) {
                val item = queue.poll()
                dir4.forEach {
                    val nextX = it[0] + item[0]
                    val nextY = it[1] + item[1]
                    if (nextX in 0 until n && nextY in 0 until m) {
                        if (g[nextX][nextY] != -1 && "$nextX,$nextY" !in seen) {
                            g[nextX][nextY] = step
                            seen.add("$nextX,$nextY")
                            queue.offer(intArrayOf(nextX, nextY))
                        }
                    }
                }
            }
        }
        // 火燃烧的时间点
        // -2 永远不会着火
        // -1 墙
        // other 着火时间点
//        g.print()

        // 等待step 是否还能走出去
        fun check(step: Long): Boolean {
            seen.clear()
            seen.add("0,0")
            val queue: Queue<IntArray> = LinkedList<IntArray>()
            queue.offer(intArrayOf(0, 0))
            var cur = 0
            while (queue.isNotEmpty()) {
                val size = queue.size
                cur++
                for (i in 0 until size) {
                    val item = queue.poll()
                    if (item[0] == n - 1 && item[1] == m - 1) return true

                    dir4.forEach {
                        val nextX = it[0] + item[0]
                        val nextY = it[1] + item[1]
                        if (nextX in 0 until n && nextY in 0 until m) {
                            if (g[nextX][nextY] != -1 && "$nextX,$nextY" !in seen) {
                                if (cur + step < g[nextX][nextY] || g[nextX][nextY] == -2 || (nextX == n - 1 && nextY == m - 1 && cur + step == g[nextX][nextY].toLong())) {
                                    seen.add("$nextX,$nextY")
                                    queue.offer(intArrayOf(nextX, nextY))
                                }
                            }
                        }
                    }
                }
            }
            return false
        }

        val ans = biMax(l = 0L, r = 10e8.toLong()) {
            check(it)
        }
        return ans.toInt()
    }
}