package leetcode.contest.c403

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.minimumSum("[[1,0,1],[1,1,1]]".toGrid()).print()
}

// 矩形分割
// https://leetcode.cn/circle/discuss/9NYliL/
class SolutionD {
    fun minimumSum(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size

        var ans = Int.MAX_VALUE / 2

        fun minArea(U: Int, D: Int, L: Int, R: Int): Int {
            var u = Int.MAX_VALUE
            var d = Int.MIN_VALUE
            var l = Int.MAX_VALUE
            var r = Int.MIN_VALUE
            for (i in U..D) {
                for (j in L..R) {
                    if (grid[i][j] == 1) {
                        u = minOf(u, i)
                        d = maxOf(d, i)
                        l = minOf(l, j)
                        r = maxOf(r, j)
                    }
                }
            }
            if (u > d || l > r) return Int.MAX_VALUE
            return (d - u + 1) * (r - l + 1)
        }

        // 横横型
        for (i in 0 until n - 1) {
            for (ii in i + 1 until n - 1) {
                ans = minOf(
                    ans,
                    minArea(0, i, 0, m - 1) + minArea(i + 1, ii, 0, m - 1) + minArea(ii + 1, n - 1, 0, m - 1)
                )
            }
        }

        // 竖竖型
        for (j in 0 until m - 1) {
            for (jj in j + 1 until m - 1) {
                ans = minOf(
                    ans,
                    minArea(0, n - 1, 0, j) + minArea(0, n - 1, j + 1, jj) + minArea(0, n - 1, jj + 1, m - 1)
                )
            }
        }

        for (i in 0 until n - 1) {
            for (j in 0 until m - 1) {
                // 横竖型
                ans = minOf(
                    ans,
                    minArea(0, i, 0, j) + minArea(0, i, j + 1, m - 1) + minArea(i + 1, n - 1, 0, m - 1)
                )
                ans = minOf(
                    ans,
                    minArea(0, i, 0, m - 1) + minArea(i + 1, n - 1, 0, j) + minArea(i + 1, n - 1, j + 1, m - 1)
                )
                // 竖横型
                ans = minOf(
                    ans,
                    minArea(0, i, 0, j) + minArea(i + 1, n - 1, 0, j) + minArea(0, n - 1, j + 1, m - 1)
                )
                ans = minOf(
                    ans,
                    minArea(0, n - 1, 0, j) + minArea(0, i, j + 1, m - 1) + minArea(i + 1, n - 1, j + 1, m - 1)
                )
            }
        }

        return ans
    }

    data class Rect(val x0: Int, val y0: Int, val x1: Int, val y1: Int) {

        val area: Int
            get() = (x1 - x0 + 1) * (y1 - y0 + 1)

        override fun toString(): String {
            return "$x0..$x1 with $y0..$y1"
        }

        operator fun contains(p: Point): Boolean {
            return (p.x in x0..x1) && (p.y in y0..y1)
        }
    }

    infix fun Rect.and(other: Rect): Rect? {
        if (other.x1 < this.x0 ||
            other.x0 > this.x1 ||
            other.y1 < this.y0 ||
            other.y0 > this.y1
        ) return null
        return Rect(
            maxOf(this.x0, other.x0),
            minOf(this.x1, other.x1),
            maxOf(this.y0, other.y0),
            minOf(this.y1, other.y1)
        )
    }

    data class Point(val x: Int, val y: Int)
}