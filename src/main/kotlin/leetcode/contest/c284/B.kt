package leetcode.contest.c284

import utils.*

fun main() {
    val s = SolutionB()
    s.digArtifacts(2, "[[0,0,0,0],[0,1,1,1]]".toGrid(), "[[0,0],[0,1]]".toGrid()).print()
}

class SolutionB {
    fun digArtifacts(n: Int, artifacts: Array<IntArray>, dig: Array<IntArray>): Int {
        val m = Array<IntArray>(n) { IntArray(n) }
        dig.forEach {
            m[it[0]][it[1]] = 1
        }
        val matrix = Matrix(n, n, m)
        matrix.preSum()
        var ans = 0
        artifacts.forEach {
            val (r1, c1, r2, c2) = it
            val need = (r2 - r1 + 1) * (c2 - c1 + 1)
            val total = matrix.subMatrixSum(r1, c1, r2, c2)
            total.print()
            if (need == total) {
                ans++
            }
        }
        return ans
    }
}