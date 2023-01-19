package leetcode.contest.c328

import utils.Matrix

// 二维差分
class SolutionB {
    fun rangeAddQueries(n: Int, queries: Array<IntArray>): Array<IntArray> {
        val m = Matrix(n, n, Array<IntArray>(n) { IntArray(n) })
        queries.forEach {
            m.matrix[it[0]][it[1]]++
            if (it[3] + 1 < n)
                m.matrix[it[0]][it[3] + 1]--
            if (it[2] + 1 < n)
                m.matrix[it[2] + 1][it[1]]--
            if (it[2] + 1 < n && it[3] + 1 < n)
                m.matrix[it[2] + 1][it[3] + 1]++
        }
        m.preSum()
        return m.sumDp
    }
}