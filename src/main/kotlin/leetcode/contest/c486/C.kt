package leetcode.contest.c486

import utils.dijkstra
import utils.print
import utils.toGraph
import utils.toGrid

fun main() {
    val s = SolutionC()
    // 3
    s.specialNodes(4, "[[0,1],[0,2],[0,3]]".toGrid(), 1, 2, 3).print()
    // 0
    s.specialNodes(4, "[[0,1],[1,2],[2,3]]".toGrid(), 0, 2, 3).print()
    // 1
    s.specialNodes(4, "[[0,1],[1,2],[1,3]]".toGrid(), 1, 3, 0).print()
}

class SolutionC {
    fun specialNodes(n: Int, edges: Array<IntArray>, x: Int, y: Int, z: Int): Int {

        val g = edges.toGraph(n)

        val dx = g.dijkstra(x)
        val dy = g.dijkstra(y)
        val dz = g.dijkstra(z)

        var ans = 0
        for (i in 0 until n) {
            val a = dx[i]
            val b = dy[i]
            val c = dz[i]

            val arr = longArrayOf(a, b, c)
            arr.sort()

            if (arr[0] * arr[0] + arr[1] * arr[1] == arr[2] * arr[2]) {
                ans++
            }
        }

        return ans
    }
}