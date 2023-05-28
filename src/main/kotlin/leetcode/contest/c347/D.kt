package leetcode.contest.c347

import utils.print
import utils.toGrid
import java.util.*


fun main() {
    val s = SolutionD()
    s.maxIncreasingCells("[[3,1],[3,4]]".toGrid()).print()
    s.maxIncreasingCells("[[1,1],[1,1]]".toGrid()).print()
    s.maxIncreasingCells("[[3,1,6],[-9,5,7]]".toGrid()).print()

    val grid = Array<IntArray>(1000) { IntArray(100) }
    s.maxIncreasingCells(grid).print()
}

class SolutionD {
    fun maxIncreasingCells(mat: Array<IntArray>): Int {
        val g = TreeMap<Int, MutableList<IntArray>>()
        val m = mat.size
        val n = mat[0].size
        for (i in 0 until m)
            for (j in 0 until n)
                g.computeIfAbsent(mat[i][j]) { k: Int -> ArrayList() }.add(intArrayOf(i, j))
        var ans = 0
        val rowMax = IntArray(m)
        val colMax = IntArray(n)
        // 从小到大排序
        for (pos in g.values) {
            val mx = IntArray(pos.size)
            for (i in pos.indices) {
                mx[i] = maxOf(rowMax[pos[i][0]], colMax[pos[i][1]]) + 1
                ans = maxOf(ans, mx[i])
            }
            for (k in pos.indices) {
                val i = pos[k][0]
                val j = pos[k][1]
                rowMax[i] = maxOf(rowMax[i], mx[k])
                colMax[j] = maxOf(colMax[j], mx[k])
            }
        }
        return ans
    }
}