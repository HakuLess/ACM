package leetcode.contest.b67

import utils.Graph
import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionC()
    s.maximumDetonation("[[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]".toGrid()).print()
    s.maximumDetonation("[[1,1,100000],[100000,100000,1]]".toGrid()).print()
//    s.maximumDetonation("[[2,1,3],[6,1,4]]".toGrid()).print()
}

class SolutionC {
    fun maximumDetonation(bombs: Array<IntArray>): Int {
        val g = Graph(bombs.size)
        for (i in bombs.indices) {
            for (j in i + 1 until bombs.size) {
                val a = bombs[i]
                val b = bombs[j]
                if ((a[0] - b[0]).toLong() * (a[0] - b[0]) + (a[1] - b[1]).toLong() * (a[1] - b[1]) <= a[2].toLong() * a[2]) {
                    g.addEdgeOri(i, j)
                }
                if ((a[0] - b[0]).toLong() * (a[0] - b[0]) + (a[1] - b[1]).toLong() * (a[1] - b[1]) <= b[2].toLong() * b[2]) {
                    g.addEdgeOri(j, i)
                }
            }
        }
        g.adj.forEach {
            println(it)
        }

        var ans = 0
        for (start in bombs.indices) {
            var cur = 0
            val seen = BooleanArray(bombs.size)
            val q: Queue<Int> = LinkedList<Int>()
            q.offer(start)
            while (q.isNotEmpty()) {
                val size = q.size
                for (i in 0 until size) {
                    val item = q.poll()
                    if (seen[item]) continue
                    seen[item] = true
                    cur++
                    g.adj[item].forEach {
                        q.offer(it)
                    }
                }
            }
            ans = maxOf(ans, cur)
        }
        return ans
    }
}