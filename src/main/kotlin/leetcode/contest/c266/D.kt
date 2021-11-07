package leetcode.contest.c266

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.maximalPathQuality(intArrayOf(0, 32, 10, 43), "[[0,1,10],[1,2,15],[0,3,10]]".toGrid(), 49).print()
}

class SolutionD {
    fun maximalPathQuality(values: IntArray, edges: Array<IntArray>, maxTime: Int): Int {
        // IntArray nextPoint, cost
        val map = HashMap<Int, ArrayList<IntArray>>()
        for (i in edges.indices) {
            map[edges[i][0]] = map.getOrDefault(edges[i][0], arrayListOf())
            map[edges[i][0]]!!.add(intArrayOf(edges[i][1], edges[i][2]))
            map[edges[i][1]] = map.getOrDefault(edges[i][1], arrayListOf())
            map[edges[i][1]]!!.add(intArrayOf(edges[i][0], edges[i][2]))
        }

        var ans = values[0]
        fun dfs(cur: Int, cost: Int, value: Int, seen: IntArray) {
            if (cost > maxTime) return
            if (cur == 0) ans = maxOf(ans, value)
            map[cur]?.forEach {
                seen[it[0]]++
                val nextValue = if (seen[it[0]] != 1) value else value + values[it[0]]
                dfs(it[0], cost + it[1], nextValue, seen)
                seen[it[0]]--
            }
        }

        dfs(0, 0, 0, IntArray(values.size))
        return ans
    }
}