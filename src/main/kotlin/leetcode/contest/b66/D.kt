package leetcode.contest.b66

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.countPyramids("[[0,0,0,1,0,0,0],[0,0,1,1,1,0,0],[0,1,1,1,1,0,0],[1,1,1,1,1,1,1]]".toGrid()).print()
}

class SolutionD {
    fun countPyramids(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val g = Array<IntArray>(m) { IntArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == 1) {
                    if (j != 0)
                        g[i][j] = g[i][j - 1] + 1
                    else
                        g[i][j] = 1
                } else {
                    g[i][j] = 0
                }
            }
        }

        var ans = 0
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == 1) {
                    var nextI = i + 1
                    var nextJ = j + 1
                    var step = 3
                    while (nextI in 0 until m && nextJ in 0 until n) {
                        if (g[nextI][nextJ] >= step) {
                            ans++
                        } else {
                            break
                        }
                        nextI++
                        nextJ++
                        step += 2
                    }
                }
            }
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == 1) {
                    var nextI = i - 1
                    var nextJ = j + 1
                    var step = 3
                    while (nextI in 0 until m && nextJ in 0 until n) {
                        if (g[nextI][nextJ] >= step) {
                            ans++
                        } else {
                            break
                        }
                        nextI--
                        nextJ++
                        step += 2
                    }
                }
            }
        }
        g.print()
        return ans
    }
}