package leetcode.contest.c372

import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionD()
//    s.leftmostBuildingQueries(intArrayOf(6, 4, 8, 5, 2, 7), "[[0,1],[0,3],[2,4],[3,4],[2,2]]".toGrid()).print()
    s.leftmostBuildingQueries(intArrayOf(5, 3, 8, 2, 6, 1, 4, 6), "[[0,7],[3,5],[5,2],[3,0],[1,6]]".toGrid()).print()
}

class SolutionD {
    fun leftmostBuildingQueries(heights: IntArray, queries: Array<IntArray>): IntArray {
        val n = queries.size
        val ids = IntRange(0, n - 1).toList().toTypedArray()
        // 坐标x 由大大小排序
        // 对id进行排序
        ids.sortBy { maxOf(queries[it][0], queries[it][1]) }
        queries.forEach {
            it.sort()
        }
        // value index
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        var cur = 0
        val ans = IntArray(n) { -1 }

        for (i in heights.indices) {
            while (cur in ids.indices) {
                val index = ids[cur]
                if (queries[index][1] == i) {
                    if (heights[queries[index][1]] > heights[queries[index][0]]) {
                        ans[index] = i
                    } else {
                        pq.offer(Pair(maxOf(heights[queries[index][0]], heights[queries[index][1]]), index))
                    }
                    cur++
                } else {
                    break
                }
            }

            while (pq.isNotEmpty() && pq.peek().first <= heights[i]) {
                val item = pq.poll()
                ans[item.second] = i
            }
        }

        for (i in queries.indices) {
            if (queries[i][0] == queries[i][1]) {
                ans[i] = queries[i][0]
            }
        }

        return ans
    }
}