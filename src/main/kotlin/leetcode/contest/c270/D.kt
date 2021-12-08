package leetcode.contest.c270

import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionD()

//    s.validArrangement("[[1,2],[1,3],[2,1]]".toGrid()).print()
    s.validArrangement("[[8,5],[8,7],[0,8],[0,5],[7,0],[5,0],[0,7],[8,0],[7,8]]".toGrid()).print()
}

// 欧拉通路
// 欧拉图
class SolutionD {
    fun validArrangement(pairs: Array<IntArray>): Array<IntArray> {
        val map = HashMap<Int, Stack<IntArray>>()
        val deg = HashMap<Int, Int>()
        pairs.forEach {
            map[it[0]] = map.getOrDefault(it[0], Stack())
            map[it[0]]!!.push(it)
            deg[it[0]] = deg.getOrDefault(it[0], 0) - 1
            deg[it[1]] = deg.getOrDefault(it[1], 0) + 1
        }

        var start = pairs[0][0]
        for (key in deg.keys) {
            // 入度比出度大1，则以该点为起始点
            if (deg[key] == -1) {
                start = key
                break
            }
        }

        val ans = ArrayList<IntArray>()
        fun dfs(cur: Int, arr: ArrayList<IntArray>) {
            while (map[cur]?.isNotEmpty() == true) {
                val next = map[cur]?.pop() ?: return
                dfs(next[1], arr)
                arr.add(next)
            }
        }
        dfs(start, ans)
        return ans.reversed().toTypedArray()
    }
}