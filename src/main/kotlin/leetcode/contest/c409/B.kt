package leetcode.contest.c409

import java.util.*

class SolutionB {
    fun shortestDistanceAfterQueries(n: Int, queries: Array<IntArray>): IntArray {
        val graph = Array(n) { mutableListOf<Int>() }

        for (i in 0 until n - 1) {
            graph[i].add(i + 1)
        }

        fun bfs(start: Int, end: Int): Int {
            val queue: Queue<Pair<Int, Int>> = LinkedList()
            val visited = BooleanArray(n)
            queue.add(Pair(start, 0))
            visited[start] = true

            while (queue.isNotEmpty()) {
                val (current, depth) = queue.poll()
                if (current == end) {
                    return depth
                }
                for (neighbor in graph[current]) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true
                        queue.add(Pair(neighbor, depth + 1))
                    }
                }
            }
            return -1
        }

        val answer = IntArray(queries.size)

        for ((index, query) in queries.withIndex()) {
            val (u, v) = query
            graph[u].add(v)
            answer[index] = bfs(0, n - 1)
        }

        return answer
    }
}