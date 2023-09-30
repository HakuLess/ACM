package leetcode.contest.c364

import utils.*
import java.util.*

fun main() {
    val s = SolutionD()
    s.countPaths(5, "[[1,2],[1,3],[2,4],[2,5]]".toGrid()).print()
    s.countPaths(6, "[[1,2],[1,3],[2,4],[3,5],[3,6]]".toGrid()).print()

    // 3
    s.countPaths(5, "[[1,3],[4,3],[2,3],[5,2]]".toGrid()).print()
}

class SolutionD {
    fun countPaths(n: Int, edges: Array<IntArray>): Long {
        val adj: Array<LinkedList<Int>> = Array(n + 1) { LinkedList<Int>() }
        edges.forEach {
            val (i, j) = it
            adj[i].add(j)
            adj[j].add(i)
        }

        var ans = 0L
        var c = 0L

        fun dfs(x: Int, pre: Int) {
            if (isPrime(x)) return
            c++
            for (y in adj[x]) {
                if (y != pre)
                    dfs(y, x)
            }
        }


        for (i in 1..n) {
            if (!isPrime(i)) continue

            var s = 0L
            adj[i].forEach {
                c = 0L
                dfs(it, -1)
                ans += c * s
                s += c
            }
            ans += s
        }

        return ans
    }
}