package leetcode.contest.c432

import utils.*

fun main() {
    val s = SolutionC()
    // 1
    s.minMaxWeight(5, "[[1,0,1],[2,0,2],[3,0,1],[4,3,1],[2,1,1]]".toGrid(), 2).print()
    // -1
    s.minMaxWeight(5, "[[0,1,1],[0,2,2],[0,3,1],[0,4,1],[1,2,1],[1,4,1]]".toGrid(), 1).print()
    // 2
    s.minMaxWeight(5, "[[1,2,1],[1,3,3],[1,4,5],[2,3,2],[3,4,2],[4,0,1]]".toGrid(), 1).print()
    // -1
    s.minMaxWeight(5, "[[1,2,1],[1,3,3],[1,4,5],[2,3,2],[4,0,1]]".toGrid(), 1).print()

    // 78
    s.minMaxWeight(4, "[[2,0,39],[2,1,72],[2,3,67],[1,2,78],[3,0,10],[0,2,81]]".toGrid(), 2).print()
}

class SolutionC {
    fun minMaxWeight(n: Int, edges: Array<IntArray>, threshold: Int): Int {
        val list = edges.map { it[2] }.toList().sorted()

        val reverseG = Graph(n)
        edges.sortedBy { -it[2] }.forEach {
            reverseG.addEdgeOri(it[1], it[0], it[2])
        }

        var ans = biMin(0L, list.lastIndex.toLong()) { index ->
            val i = index.toInt()
            val max = list[i]
            reverseG.canReachAll(0, maxWeight = max)
        }.toInt()

        if (ans == -1) return ans
        return list[ans]
    }
}