package leetcode.contest.c341

import utils.Graph
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    s.minimumTotalPrice(
        4, "[[0,1],[1,2],[1,3]]".toGrid(), intArrayOf(2, 2, 10, 6),
        "[[0,3],[2,1],[2,3]]".toGrid()
    ).print()
}

class SolutionD {
    fun minimumTotalPrice(n: Int, edges: Array<IntArray>, price: IntArray, trips: Array<IntArray>): Int {
        val g = Graph(n)
        edges.forEach {
            g.addEdge(it[0], it[1])
        }

        fun getRoute(start: Int, end: Int): ArrayList<Int> {
            val queue: Queue<Pair<Int, ArrayList<Int>>> = LinkedList()
            queue.offer(Pair(start, arrayListOf(start)))
            val seen = HashSet<Int>()
            while (queue.isNotEmpty()) {
                val size = queue.size
                for (i in 0 until size) {
                    val (item, l) = queue.poll()
                    seen.add(item)
                    if (item == end) return l
                    g.adj[item].forEach {
                        if (it !in seen) {
                            val nl = l.clone() as ArrayList<Int>
                            nl.add(it)
                            queue.offer(Pair(it, nl))
                        }
                    }
                }
            }
            return arrayListOf()
        }

        val cnt = IntArray(n)

        for ((start, end) in trips) {
            val r = getRoute(start, end)
            r.forEach {
                cnt[it] += price[it]
            }
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