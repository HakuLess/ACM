package leetcode.contest.b77

import utils.dir4
import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionC()
    s.countUnguarded(4, 6, "[[0,0],[1,1],[2,3]]".toGrid(), "[[0,1],[2,2],[1,4]]".toGrid()).print()
}

class SolutionC {
    fun countUnguarded(m: Int, n: Int, guards: Array<IntArray>, walls: Array<IntArray>): Int {
        val grid = Array<IntArray>(m) { IntArray(n) }
        // 1 wall
        // 2 guard
        // 3 seen
        walls.forEach {
            grid[it[0]][it[1]] = 1
        }
        guards.forEach {
            grid[it[0]][it[1]] = 2
        }
        guards.forEach {
            dir4.forEach { dir ->
                var step = 1
                while (true) {
                    val nextX = it[0] + step * dir[0]
                    val nextY = it[1] + step * dir[1]
                    if (nextX !in grid.indices || nextY !in grid[0].indices) {
                        break
                    }
                    if (grid[nextX][nextY] == 2 || grid[nextX][nextY] == 1) {
                        break
                    }
                    grid[nextX][nextY] = 3
                    step++
                }
            }
        }
        var ans = 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == 0) ans++
            }
        }
//        grid.print()
        return ans
    }
}