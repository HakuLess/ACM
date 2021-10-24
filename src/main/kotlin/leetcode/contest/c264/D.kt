package leetcode.contest.c264

import utils.print
import utils.toGrid

fun main() {
    val s = Solution5909()
    s.minimumTime(3, "[[1,3],[2,3]]".toGrid(), intArrayOf(3, 2, 5)).print()
    s.minimumTime(5, "[[1,5],[2,5],[3,5],[3,4],[4,5]]".toGrid(), intArrayOf(1, 2, 3, 4, 5)).print()
}

class Solution5909 {
    fun minimumTime(n: Int, relations: Array<IntArray>, time: IntArray): Int {
        val edges = HashMap<Int, ArrayList<Int>>()
        relations.forEach {
            edges[it[1]] = edges.getOrDefault(it[1], arrayListOf())
            edges[it[1]]!!.add(it[0])
        }

        val seen = IntArray(n + 1)

        fun dfs(cur: Int): Int {
            if (seen[cur] != 0) return seen[cur]

            var max = 0
            edges.getOrDefault(cur, arrayListOf()).forEach {
                max = maxOf(max, dfs(it))
            }
            max += time[cur - 1]
            return max.also {
                seen[cur] = max
            }
        }

        for (i in 1..n) {
            dfs(i)
        }

//        seen.print()
//        return seen.max()!!
        return seen.maxOrNull()!!
    }
}