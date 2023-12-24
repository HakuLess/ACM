package leetcode.contest.c377

import utils.Graph
import utils.floyd
import utils.print

fun main() {
    val s = SolutionC()
    s.minimumCost(
        "abcd",
        "acbe",
        charArrayOf('a', 'b', 'c', 'c', 'e', 'd'),
        charArrayOf('b', 'c', 'b', 'e', 'b', 'e'),
        intArrayOf(2, 5, 5, 1, 2, 20)
    ).print()

    s.minimumCost(
        "aaaa",
        "bbbb",
        charArrayOf('a', 'c'),
        charArrayOf('c', 'b'),
        intArrayOf(1, 2)
    ).print()

    s.minimumCost(
        "aaaa",
        "bbbb",
        charArrayOf('a', 'c'),
        charArrayOf('c', 'e'),
        intArrayOf(1, 2)
    ).print()
}

class SolutionC {
    fun minimumCost(source: String, target: String, original: CharArray, changed: CharArray, cost: IntArray): Long {
        val graph = Graph(26)
        for (i in original.indices) {
            val a = original[i] - 'a'
            val b = changed[i] - 'a'
            if (graph.weight[a]!!.getOrDefault(b, Int.MAX_VALUE) > cost[i]) {
                graph.addEdgeOri(a, b, cost[i])
            }
        }
        val floyd = graph.floyd()
        var ans = 0L
        for (i in source.indices) {
            val a = source[i] - 'a'
            val b = target[i] - 'a'
            if (floyd[a][b] == Long.MAX_VALUE / 2) return -1
//            println("$i: change $a to $b with ${floyd[a][b]}")
            ans += floyd[a][b]
        }
        return ans
    }
}