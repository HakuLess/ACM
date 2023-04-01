package leetcode.contest.c338

import utils.Graph
import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionD()
    // 0
    s.collectTheCoins(intArrayOf(1, 1, 1, 1), "[[0,1],[1,2],[2,3]]".toGrid()).print()

    // 22
//    s.collectTheCoins(
//        intArrayOf(1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1),
//        "[[0,1],[1,2],[2,3],[3,4],[4,5],[5,6],[6,7],[7,8],[8,9],[8,10],[9,11],[10,12],[11,13],[13,14],[13,15],[15,16],[16,17],[17,18]]".toGrid()
//    ).print()
}

// Rank 54	时光放逐
class SolutionD {

    fun collectTheCoins(coins: IntArray, edges: Array<IntArray>): Int {
        val n = coins.size
        val g = Graph(n)
        edges.forEach {
            val (i, j) = it
            g.addEdge(i, j, 1)
        }

        val queue: Queue<Int> = LinkedList<Int>()
        for (i in 0 until n) {
            // 无金币叶子
            if (g.adj[i].size == 1 && coins[i] == 0) {
                queue.offer(i)
            }
        }

        // 将无用边删除
        while (queue.isNotEmpty()) {
            val item = queue.poll()
            g.adj[item].forEach {
                g.adj[it].remove(item)
                if (g.adj[it].size == 1 && coins[it] == 0) {
                    queue.offer(it)
                }
            }
            g.adj[item].clear()
        }

        queue.clear()
        for (i in 0 until n) {
            if (g.adj[i].size == 1) {
                queue.offer(i)
            }
        }

        // 保留边都向内进2步
        var step = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            if (step == 2) break
            step++
            for (i in 0 until size) {
                val item = queue.poll()
                g.adj[item].forEach {
                    g.adj[it].remove(item)
                    if (g.adj[it].size == 1) {
                        queue.offer(it)
                    }
                }
                g.adj[item].clear()
            }
        }

        var ans = 0
        g.adj.forEach {
            ans += it.size
        }
        return ans
    }

}