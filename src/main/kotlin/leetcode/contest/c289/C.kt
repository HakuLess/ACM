package leetcode.contest.c289

import utils.*

fun main() {
    val s = SolutionC()
//    s.maxTrailingZeros("[[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]".toGrid()).print()
    // 5
    s.maxTrailingZeros("[[899,727,165,249,531,300,542,890],[981,587,565,943,875,498,582,672],[106,902,524,725,699,778,365,220]]".toGrid())
        .print()
//    s.maxTrailingZeros("[[10],[6],[15]]".toGrid()).print()

}

// Not Finish
class SolutionC {
    fun maxTrailingZeros(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size

        val g2 = Array<IntArray>(n) { IntArray(m) }
        val m2 = Matrix(n, m, g2)
        val g5 = Array<IntArray>(n) { IntArray(m) }
        val m5 = Matrix(n, m, g5)
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                var item = grid[i][j]
                var a = 0
                var b = 0
                while (item % 2 == 0) {
                    a++
                    item /= 2
                }
                while (item % 5 == 0) {
                    b++
                    item /= 5
                }
                g2[i][j] = a
                g5[i][j] = b
//                println("$i $j ${Pair(a, b)}")
            }
        }

        m2.preSum()
        m5.preSum()

        var ans = 0

        for (i in 0 until n) {
            for (j in 0 until m) {
                // 左 右 上 下
                val a2 = m2.subMatrixSum(i, 0, i, j)
                val b2 = m2.subMatrixSum(i, j, i, m - 1)
                val c2 = m2.subMatrixSum(0, j, i, j)
                val d2 = m2.subMatrixSum(i, j, n - 1, j)

                val a5 = m5.subMatrixSum(i, 0, i, j)
                val b5 = m5.subMatrixSum(i, j, i, m - 1)
                val c5 = m5.subMatrixSum(0, j, i, j)
                val d5 = m5.subMatrixSum(i, j, n - 1, j)

                var res = minOf(a2 + c2 - g2[i][j], a5 + c5 - g5[i][j])
                res = maxOf(res, minOf(a2 + d2 - g2[i][j], a5 + d5 - g5[i][j]))
                res = maxOf(res, minOf(b2 + c2 - g2[i][j], b5 + c5 - g5[i][j]))
                res = maxOf(res, minOf(b2 + d2 - g2[i][j], b5 + d5 - g5[i][j]))
                ans = maxOf(ans, res)
            }
        }

        return ans
    }
}