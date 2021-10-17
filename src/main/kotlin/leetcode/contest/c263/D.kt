package leetcode.contest.c263

import utils.Graph
import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = Solution5905()
    s.secondMinimum(5, "[[1,2],[1,3],[1,4],[3,4],[4,5]]".toGrid(), 3, 5).print()
    s.secondMinimum(2, "[[1,2]]".toGrid(), 3, 2).print()
}

class Solution5905 {
    fun secondMinimum(n: Int, edges: Array<IntArray>, time: Int, change: Int): Int {
        val g = Graph(n)
        edges.forEach {
            g.addEdge(it[0] - 1, it[1] - 1, time)
        }

        val seen = IntArray(n)
        val meet = IntArray(n) { -1 }

        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        pq.offer(Pair(0, 0))

        var max = -1
        while (pq.isNotEmpty()) {
            val item = pq.poll()
//            println("进入 $item, 过去访问状态${seen[item.first]}")
            if (meet[item.first] == item.second) continue
            seen[item.first]++
            meet[item.first] = item.second

            if (item.first == n - 1) {
                if (max == -1) {
                    max = item.second
                } else if (item.second != max) {
                    return item.second
                }
            }
            if ((item.second / change) % 2 != 0) {
                // 不可移动
                val wait = (item.second / change + 1) * change
//                println("红灯等待 offer ${Pair(item.first, wait)}")
                g.adj[item.first].forEach {
//                    println("红灯后，绿灯移动 offer ${Pair(it, wait + time)}")
                    if (seen[it] <= 2)
                        pq.offer(Pair(it, wait + time))
                }
            } else {
                // 可以移动
                g.adj[item.first].forEach {
//                    println("绿灯移动 offer ${Pair(it, item.second + time)}")
                    if (seen[it] <= 2)
                        pq.offer(Pair(it, item.second + time))
                }
            }
        }
        return -1
    }
}