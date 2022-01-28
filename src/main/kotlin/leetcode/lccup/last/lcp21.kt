package leetcode.lccup.last

import utils.*

fun main() {
    val s = SolutionLCP21()
    s.chaseGame("[[1,2],[2,3],[3,4],[4,1],[2,5],[5,6]]".toGrid(), 3, 5).print()
//    s.chaseGame("[[1,2],[2,3],[3,4],[4,1]]".toGrid(), 1, 3).print()
//    s.chaseGame("[[1,2],[2,3],[3,4],[4,1]]".toGrid(), 1, 2).print()
}

class SolutionLCP21 {
    fun chaseGame(edges: Array<IntArray>, startA: Int, startB: Int): Int {
        // 边与点的数量相同，且完全连通，则一定有且只有一个环
        val n = edges.size
        val g = Graph(n)
        edges.forEach {
            if (it[0] == startA && it[1] == startB) return 1
            if (it[0] == startB && it[1] == startA) return 1
            g.addEdge(it[0] - 1, it[1] - 1, 1)
        }

        val disA = g.dijkstra(startA - 1)
        val disB = g.dijkstra(startB - 1)

        val isCircle = BooleanArray(n)
        var circleSize: Int
        g.findCircle().let {
            circleSize = it.size
            it.forEach {
                isCircle[it] = true
            }
        }

//        disA.print()
//        disB.print()
        var ans = 0L
        for (i in 0 until n) {
            if (circleSize > 3 && isCircle[i] && disB[i] + 1 < disA[i]) {
                return -1
            }
            if (disB[i] + 1 < disA[i]) {
                ans = maxOf(ans, disA[i])
            }
        }
        return ans.toInt()
    }
}