package leetcode.contest.c341

import utils.Graph
import utils.print
import utils.toGrid
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    s.minimumTotalPrice(
        4, "[[0,1],[1,2],[1,3]]".toGrid(), intArrayOf(2, 2, 10, 6),
        "[[0,3],[2,1],[2,3]]".toGrid()
    ).print()

//    s.minimumTotalPrice(
//        40,
//        "[[0,28],[6,29],[7,34],[8,5],[5,20],[9,12],[12,3],[13,11],[14,32],[18,3],[3,20],[22,15],[15,28],[26,25],[25,20],[20,17],[27,16],[28,2],[31,2],[2,21],[21,23],[23,4],[4,35],[32,19],[33,39],[34,10],[10,11],[11,16],[16,17],[17,1],[1,24],[24,30],[30,19],[19,39],[35,29],[29,38],[36,38],[37,39],[38,39]]".toGrid(),
//        intArrayOf(
//            4,
//            14,
//            4,
//            8,
//            26,
//            26,
//            12,
//            6,
//            10,
//            30,
//            30,
//            28,
//            2,
//            20,
//            8,
//            26,
//            10,
//            30,
//            18,
//            30,
//            18,
//            30,
//            16,
//            14,
//            18,
//            6,
//            20,
//            24,
//            20,
//            18,
//            8,
//            4,
//            12,
//            30,
//            12,
//            6,
//            30,
//            22,
//            28,
//            8
//        ),
//        "[[10,15],[5,21],[16,28],[0,31],[13,37],[22,27],[13,7],[23,10],[7,4],[0,11],[35,20],[7,12],[16,15],[21,6],[7,4],[5,25],[10,22],[10,1],[20,8],[20,23],[38,39],[20,2]]".toGrid()
//    ).print()
}

class SolutionD {
    fun minimumTotalPrice(n: Int, edges: Array<IntArray>, price: IntArray, trips: Array<IntArray>): Int {
        val g = Graph(n)
        edges.forEach {
            g.addEdge(it[0], it[1])
        }

        val cnt = IntArray(n)
        fun dfs(x: Int, pre: Int, end: Int): Boolean {
            if (x == end) {
                cnt[x] += price[x]
                return true
            }
            for (y in g.adj[x]) {
                if (y != pre && dfs(y, x, end)) {
                    cnt[x] += price[x]
                    return true
                }
            }
            return false
        }


        for ((start, end) in trips) {
            dfs(start, -1, end)
        }

        fun dfs(tree: Array<out List<Int>>, f: Array<IntArray>, valArray: IntArray, u: Int, father: Int) {
            // 选u的子树，u必须被选择
            f[u][1] = valArray[u] / 2
            // 不选u的子树，u可以选择或不选择
            f[u][0] = valArray[u]

            for (v in tree[u]) {
                if (v == father) continue
                dfs(tree, f, valArray, v, u)
                f[u][1] += f[v][0]
                f[u][0] += minOf(f[v][0], f[v][1])
            }
        }

        val f = Array(n + 1) { IntArray(2) }
        val root = 0
        dfs(g.adj, f, cnt, root, 0)

        return minOf(f[root][0], f[root][1])
    }
}