package leetcode.contest.c328

import utils.Graph
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
//    s.maxOutput(6, "[[0,1],[1,2],[1,3],[3,4],[3,5]]".toGrid(), intArrayOf(9, 8, 7, 6, 10, 5)).print()
    // 39
    s.maxOutput(
        9,
        "[[1,7],[5,2],[2,3],[6,0],[0,4],[4,7],[7,3],[3,8]]".toGrid(),
        intArrayOf(6, 13, 8, 10, 4, 5, 8, 3, 12)
    ).print()
}

// todo Not Finished
class SolutionD {
    fun maxOutput(n: Int, edges: Array<IntArray>, price: IntArray): Long {
        val g = Graph(n)
        edges.forEach {
            g.addEdge(it[0], it[1])
        }

        var start = -1
        for (i in 0 until n) {
            if (g.adj[i].size == 1) {
                start = i
                break
            }
        }

        val seen = HashSet<Int>()

        var tmp = 0L

        // 返回总值及另外一个顶点
        fun dfs(c: Int, cur: Long): Pair<Long, Int> {
            var ans = price[c].toLong()
            seen.add(c)
            var second = c
            val l = ArrayList<Pair<Long, Int>>()
            g.adj[c].forEach {
                if (it !in seen) {
                    dfs(it, cur + price[c]).let { next ->
                        l.add(next)
                        if (price[c] + next.first > ans) {
                            ans = price[c] + next.first
                            second = next.second
                        }
                    }
                }
            }

            l.add(Pair(cur, start))

//            println("$c:  ${l.size} with ${l.map { Triple(it.first, it.second, price[it.second]) }}")

            if (l.size == 1) {
                tmp = maxOf(tmp, price[c] + l[0].first - price[l[0].second])
            }
            if (l.size == 2) {
                tmp = maxOf(tmp, price[c] + l[0].first + l[1].first - minOf(price[l[0].second], price[l[1].second]))
            }
            if (l.size == 3) {
                tmp = maxOf(tmp, price[c] + l[0].first + l[1].first - minOf(price[l[0].second], price[l[1].second]))
                tmp = maxOf(tmp, price[c] + l[0].first + l[2].first - minOf(price[l[0].second], price[l[2].second]))
                tmp = maxOf(tmp, price[c] + l[1].first + l[2].first - minOf(price[l[1].second], price[l[2].second]))
            }
            return Pair(ans, second)
        }

//        println("start with $start")
        dfs(start, 0L)

        return tmp
    }
}