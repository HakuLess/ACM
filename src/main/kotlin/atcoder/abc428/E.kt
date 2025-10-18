package atcoder.abc428

import java.util.LinkedList
import java.util.Queue

fun main() {
    val n = readln().toInt()
    val g = Array(n + 1) { mutableListOf<Int>() }
    repeat(n - 1) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        g[a].add(b)
        g[b].add(a)
    }

    fun bfs(start: Int): Pair<IntArray, Int> {
        val dist = IntArray(n + 1) { -1 }
        val q: Queue<Int> = LinkedList()
        q.add(start)
        dist[start] = 0
        // f 代表最远点
        var f = start
        while (q.isNotEmpty()) {
            val u = q.poll()
            for (v in g[u]) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1
                    q.add(v)
                    if (dist[v] > dist[f] || (dist[v] == dist[f] && v > f)) {
                        f = v
                    }
                }
            }
        }
        return dist to f
    }

    // 找到距离1的最远点a
    val (_, a) = bfs(1)
    // 找到每个点到a的距离，以及a的最远点b
    val (distA, b) = bfs(a)
    // 找到每个点到b的距离
    val (distB, _) = bfs(b)

    val ans = StringBuilder()
    for (v in 1..n) {
        when {
            distA[v] > distB[v] -> ans.append(a).append('\n')
            distA[v] < distB[v] -> ans.append(b).append('\n')
            else -> ans.append(maxOf(a, b)).append('\n')
        }
    }
    print(ans)
}