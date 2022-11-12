package leetcode.contest.b91

import utils.Graph
import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionC()
    s.mostProfitablePath("[[0,1],[1,2],[1,3],[3,4]]".toGrid(), 3, intArrayOf(-2, 4, 2, -4, 6)).print()
}

class SolutionC {
    fun mostProfitablePath(edges: Array<IntArray>, bob: Int, amount: IntArray): Int {
        val n = edges.size
        val g = Graph(n + 1)
        for (i in edges.indices) {
            g.addEdge(edges[i][0], edges[i][1])
        }
        val l = ArrayList<Int>()
        val seen = HashSet<Int>()
        fun dfs(cur: Int): Boolean {
            seen.add(cur)
            if (cur == bob) {
                l.add(cur)
                return true
            }
            g.adj[cur].forEach {
                if (it in seen) return@forEach
                if (dfs(it)) {
                    l.add(cur)
                    return true
                }
            }
            return false
        }
        dfs(0)

        val sz = l.size
        for (i in l.indices) {
            if (i * 2 + 1 == sz) {
                amount[l[i]] /= 2
            } else if (i * 2 < sz) {
                amount[l[i]] = 0
            }
        }

        seen.clear()
        var ans = Int.MIN_VALUE / 2
        val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
        queue.offer(Pair(0, amount[0]))
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val (index, value) = queue.poll()
                seen.add(index)
                var add = false
                g.adj[index].forEach {
                    if (it in seen) return@forEach
                    var nextValue = value
                    nextValue += amount[it]
                    queue.offer(Pair(it, nextValue))
                    add = true
                }
                if (!add) {
                    ans = maxOf(ans, value)
                }
            }
        }
        return ans
    }
}