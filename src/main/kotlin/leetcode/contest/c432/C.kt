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
        val maxWeight = edges.maxOf { it[2] }.toLong()
        var ans = biMin(1L, maxWeight) { max ->
//            println("enter $max")
            // 过滤后保留的边
            val filters = edges.filter { it[2] <= max }.toTypedArray()
            val g = Graph(n)
            filters.forEach {
                g.addEdgeOri(it[0], it[1], it[2])
            }

            var result: Boolean = true
//            // 节点出去数量不满足
//            if (g.adj.any { it.size > threshold }) {
//                result = false
//            }

//            println("enter $max with threshold $result ${g.adj.joinToString()}")

            val reverseG = Graph(n)
            filters.forEach {
                reverseG.addEdgeOri(it[1], it[0], it[2])
            }

            result = reverseG.canReachAll(0)
//            val arr = reverseG.dijkstra(0)
//            if (arr.any { it == Long.MAX_VALUE / 2 }) {
//                result = false
//            }
//            println("enter $max with arr $result ${arr.joinToString()}")

            result
        }.toInt()

//        val filters = edges.filter { it[2] <= ans }.toTypedArray()
//        val g = Graph(n)
//        filters.forEach {
//            g.addEdgeOri(it[0], it[1], it[2])
//        }
//        g.adj.joinToString().print()
//        if (g.adj.any { it.size > threshold }) {
//            ans = -1
//        }

        return ans
    }
}