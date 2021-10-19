package leetcode.contest.c263

import utils.Graph
import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = Solution5905()
    s.secondMinimum(5, "[[1,2],[1,3],[1,4],[3,4],[4,5]]".toGrid(), 3, 5).print()
//    s.secondMinimum(2, "[[1,2]]".toGrid(), 3, 2).print()
}

class Solution5905 {
    fun secondMinimum(n: Int, edges: Array<IntArray>, time: Int, change: Int): Int {
        val g = Graph(n)
        edges.forEach {
            g.addEdge(it[0] - 1, it[1] - 1, time)
        }

        val seen = Array(n) { intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE) }

        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        pq.offer(Pair(0, 0))

        var max = -1
        while (pq.isNotEmpty()) {
            val (item, curTime) = pq.poll()
            if (seen[item][1] < curTime) continue

            if (item == n - 1) {
                if (max == -1) {
                    max = curTime
                } else if (curTime != max) {
                    return curTime
                }
            }
            var nextTime = curTime + time
            if ((curTime / change) % 2 != 0) {
                // 不可移动，等待到下次绿灯
                nextTime = (curTime / change + 1) * change + time
            }
            g.adj[item].forEach {
                if (nextTime < seen[it][0]) {
                    pq.offer(Pair(it, nextTime))
                    seen[it][0] = nextTime
                } else if (nextTime > seen[it][0] && nextTime < seen[it][1]) {
                    pq.offer(Pair(it, nextTime))
                    seen[it][1] = nextTime
                }
            }
        }
        return -1
    }
}