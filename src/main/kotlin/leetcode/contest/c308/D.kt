package leetcode.contest.c308

import utils.Graph
import utils.print
import utils.toGrid
import utils.topologicalSort

fun main() {
    val s = SolutionD()
    s.buildMatrix(3, "[[1,2],[3,2]]".toGrid(), "[[2,1],[3,2]]".toGrid()).print()
    s.buildMatrix(3, "[[1,2],[2,3],[3,1],[2,3]]".toGrid(), "[[2,1]]".toGrid()).print()
}

class SolutionD {
    fun buildMatrix(k: Int, rowConditions: Array<IntArray>, colConditions: Array<IntArray>): Array<IntArray> {
        val gr = Graph(k)
        rowConditions.forEach {
            gr.addEdgeOri(it[0] - 1, it[1] - 1, 1)
        }
        val gc = Graph(k)
        colConditions.forEach {
            gc.addEdgeOri(it[0] - 1, it[1] - 1, 1)
        }
        val a = gr.topologicalSort().toIntArray()
        val b = gc.topologicalSort().toIntArray()
        if (a.isEmpty() || b.isEmpty()) return emptyArray()
        val ans = Array<IntArray>(k) { IntArray(k) }
        for (i in 0 until k) {
            ans[a.indexOf(i)][b.indexOf(i)] = i + 1
        }
        return ans
    }
}