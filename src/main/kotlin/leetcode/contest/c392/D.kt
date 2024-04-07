package leetcode.contest.c392

import utils.UFS
import utils.print
import utils.printInt
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.minimumCost(
        9,
        "[[0,4,7],[3,5,1],[1,3,5],[1,5,1]]".toGrid(),
        "[[0,4],[1,5],[3,0],[3,3],[3,2],[2,0],[7,7],[7,0]]".toGrid()
    ).print()

    // [0,-1,-1,-1,-1]
    s.minimumCost(
        9,
        "[[1,7,7],[5,6,2],[3,8,5],[3,6,3]]".toGrid(),
        "[[3,8],[1,4],[5,2],[5,2],[1,3]]".toGrid()
    ).print()
}

class SolutionD {
    fun minimumCost(n: Int, edges: Array<IntArray>, query: Array<IntArray>): IntArray {
        val map = HashMap<Int, Int>()
        val ufs = UFS(n)
        for (i in edges.indices) {
            val (a, b, e) = edges[i]

            ufs.union(a, b)
        }

        for (i in edges.indices) {
            val (a, b, e) = edges[i]
            val rootA = ufs.find(a)
            val rootB = ufs.find(b)

            val root = ufs.find(a)
            var tmp = e
            if (rootA in map.keys) {
                tmp = e and map[rootA]!!
            }
            if (rootB in map.keys) {
                tmp = e and map[rootB]!!
            }
            map[root] = tmp
        }

//        map.printInt()

        val ans = IntArray(query.size)
        for ((i, q) in query.withIndex()) {
            val (s, t) = q
            val rootA = ufs.find(s)
            val rootB = ufs.find(t)
//            println("$i: find $s root $rootA, find $t root $rootB")
            if (rootA != rootB) {
                ans[i] = -1
            } else if (s == t) {
                ans[i] = 0
            } else {
                ans[i] = map[rootA]!!
            }
        }
        return ans
    }
}