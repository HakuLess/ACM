package leetcode.contest.b160

import java.util.*

class SolutionC {
    fun minTime(n: Int, edges: Array<IntArray>): Int {

        // 有向图，添加时间 s e 数据
        val graph = Array(n) { mutableListOf<Triple<Int, Int, Int>>() }
        for ((u, v, s, e) in edges) {
            graph[u].add(Triple(v, s, e))
        }

        // 优先队列：根据当前时间升序排列
        val pq = PriorityQueue(compareBy<Pair<Int, Int>> { it.second })
        pq.offer(Pair(0, 0))

        val visited = IntArray(n) { Int.MAX_VALUE }

        while (pq.isNotEmpty()) {
            val (cur, time) = pq.poll()

            // 更早时间已访问过，则跳过
            if (time >= visited[cur]) continue

            // 刷新全局最短时间
            visited[cur] = time

            if (cur == n - 1) return time

            for ((next, start, end) in graph[cur]) {

                if (time > end) continue

                // 需要等待到开始
                val nextTime = maxOf(time, start) + 1
                if (nextTime < visited[next]) {
                    pq.offer(Pair(next, nextTime))
                }
            }
        }

        return -1
    }
}