package leetcode.contest.b114

import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    s.maxKDivisibleComponents(
        8,
        "[[0,4],[4,1],[0,3],[1,2],[0,5],[5,7],[1,6]]".toGrid(),
        intArrayOf(2, 6, 2, 2, 2, 0, 0, 0),
        7
    ).print()
    s.maxKDivisibleComponents(5, "[[0,2],[1,2],[1,3],[2,4]]".toGrid(), intArrayOf(1, 8, 1, 4, 4), 6).print()
    s.maxKDivisibleComponents(7, "[[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]".toGrid(), intArrayOf(3, 0, 6, 1, 5, 2, 1), 3)
        .print()
}

class SolutionD {
    fun maxKDivisibleComponents(n: Int, edges: Array<IntArray>, values: IntArray, k: Int): Int {
        val adj: Array<LinkedList<Int>> = Array(n) { LinkedList<Int>() }
        edges.forEach {
            val (i, j) = it
            adj[i].add(j)
            adj[j].add(i)
        }
        val map = HashMap<Int, Int>()
        // 从仅有1条边的开始删起
        val list = ArrayList<Int>()
        for (i in 0 until n) {
            if (adj[i].size == 1) {
                list.add(i)
            }
            map[i] = adj[i].size
        }
        val seen = HashSet<Int>()
        var ans = 0
        while (list.isNotEmpty()) {
            val item = list.removeAt(0)
            seen.add(item)
            if (values[item] % k == 0) {
                ans++
            } else {
                val next = adj[item].first { it !in seen }
                // 值传递给下一个
                values[next] += values[item]
            }

            adj[item].forEach {
                map[it] = map[it]!! - 1
                if (map[it] == 1) {
                    list.add(it)
                }
            }
        }
        return maxOf(ans, 1)
    }
}