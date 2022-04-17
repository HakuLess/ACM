package leetcode.contest.b76

import utils.Graph
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
//    s.maximumScore(intArrayOf(5, 2, 9, 8, 4), "[[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]".toGrid()).print()
//    s.maximumScore(intArrayOf(8, 7, 1, 26), "[[3,1],[1,2],[2,0],[0,3],[2,3],[0,1]]".toGrid()).print()
    // 44
//    s.maximumScore(
//        intArrayOf(14, 12, 10, 8, 1, 2, 3, 1),
//        "[[1,0],[2,0],[3,0],[4,0],[5,1],[6,1],[7,1],[2,1]]".toGrid()
//    ).print()

    // 63
    s.maximumScore(
        intArrayOf(24, 15, 12, 5, 2, 8, 5, 16),
        "[[1,3],[3,4],[4,7],[7,6],[6,1],[1,5],[7,2],[2,6],[1,7],[7,0],[0,4],[1,4],[3,7]]".toGrid()
    ).print()
}

class SolutionD {
    fun maximumScore(scores: IntArray, edges: Array<IntArray>): Int {
        val g = Graph(scores.size)
        edges.forEach {
            g.addEdge(it[0], it[1])
        }
        g.adj.forEach {
            it.sortByDescending { scores[it] }
        }
        var ans = -1
        // 枚举一条边 && 两个点
        for (i in edges.indices) {
            val a = edges[i][0]
            val b = edges[i][1]
            // 点a连接另一点 点b连接另一点
            for (j in 0 until 4) {
                if (j !in g.adj[a].indices) break
                val c = g.adj[a][j]
                for (k in 0 until 4) {
                    if (k !in g.adj[b].indices) break
                    val d = g.adj[b][k]
                    if (hashSetOf(a, b, c, d).size == 4) {
                        ans = maxOf(ans, scores[a] + scores[b] + scores[c] + scores[d])
                        println("$a $b $c $d $ans")
                    }
                }
            }
        }
        return ans
    }
}