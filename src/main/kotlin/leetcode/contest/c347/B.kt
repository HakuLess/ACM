package leetcode.contest.c347

import utils.print
import utils.toGrid
import kotlin.math.abs

fun main() {
    val s = SolutionB()
    s.differenceOfDistinctValues("[[1,2,3],[3,1,5],[3,2,1]]".toGrid()).print()
}

class SolutionB {
    fun differenceOfDistinctValues(grid: Array<IntArray>): Array<IntArray> {
        val ans = Array<IntArray>(grid.size) { IntArray(grid[0].size) }
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                var a = i - 1
                var b = j - 1
                // 左上角不同
                val leftTop = hashSetOf<Int>()
                while (a in grid.indices && b in grid[0].indices) {
                    leftTop.add(grid[a][b])
                    a--
                    b--
                }

                val rightBottom = hashSetOf<Int>()
                a = i + 1
                b = j + 1
                while (a in grid.indices && b in grid[0].indices) {
                    rightBottom.add(grid[a][b])
                    a++
                    b++
                }
                ans[i][j] = abs(leftTop.size - rightBottom.size)
            }
        }
        return ans
    }
}