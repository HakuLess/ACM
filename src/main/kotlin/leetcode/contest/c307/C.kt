package leetcode.contest.c307

import utils.*

fun main() {
    val s = SolutionC()
    s.amountOfTime(arrayOf(1, 5, 3, null, 4, 10, 6, 9, 2).toTree(), 3).print()
}

class SolutionC {
    fun amountOfTime(root: TreeNode?, start: Int): Int {
        val g = Graph(100005)
        fun dfs(cur: TreeNode?) {
            if (cur == null) return
            if (cur.left != null) {
                g.addEdge(cur.`val`, cur.left!!.`val`, 1)
            }
            if (cur.right != null) {
                g.addEdge(cur.`val`, cur.right!!.`val`, 1)
            }

            dfs(cur.left)
            dfs(cur.right)
        }
        dfs(root)
        g.dijkstra(start).print()
        return g.dijkstra(start).filter { it < Int.MAX_VALUE }.maxOf { it }.toInt()
    }
}