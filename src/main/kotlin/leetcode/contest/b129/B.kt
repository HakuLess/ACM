package leetcode.contest.b129

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionB()
    s.numberOfRightTriangles("[[0,1,0],[0,1,1],[0,1,0]]".toGrid()).print()
    s.numberOfRightTriangles("[[0,0],[0,1],[1,1]]".toGrid()).print()
}

class SolutionB {
    fun numberOfRightTriangles(grid: Array<IntArray>): Long {
        val h = IntArray(grid[0].size)
        val v = IntArray(grid.size)

        for (i in grid.indices) {
            for (j in grid[0].indices) {
                // 以 i,j 为左上角
                if (grid[i][j] == 1) {
                    v[i]++
                    h[j]++
                }
            }
        }

        var ans = 0L
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == 1) {
                    // 同行有
                    val hc = h[j] - 1
                    // 同列有，仅算底部
                    val vc = v[i] - 1

                    println("当前 $i $j 增加 $hc * $vc")
                    ans += 1L * hc * vc
                }
            }
        }
        return ans
    }
}