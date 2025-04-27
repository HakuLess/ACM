package leetcode.contest.c447

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.pathExistenceQueries(5, intArrayOf(1, 8, 3, 4, 2), 3, "[[0,3],[2,4]]".toGrid()).print()
}

// TODO Not Finished
class SolutionD {
    fun pathExistenceQueries(n: Int, nums: IntArray, maxDiff: Int, queries: Array<IntArray>): IntArray {

        data class Node(val value: Int, val idx: Int)

        val arr = nums.mapIndexed { i, v -> Node(v, i) }
            .sortedBy { it.value }
            .toTypedArray()

        val pos = IntArray(n).also {
            for (i in 0 until n) it[arr[i].idx] = i
        }

        val reachR = IntArray(n)
        var r = 0
        for (i in 0 until n) {
            while (r + 1 < n && arr[r + 1].value - arr[i].value <= maxDiff) {
                r++
            }
            reachR[i] = r
        }

        // 2^17=131072 > 1e5
        val maxJump = 17
        val upR = Array(maxJump + 1) { IntArray(n) }
        upR[0] = reachR.copyOf()
        for (k in 1..maxJump) {
            for (i in 0 until n) {
                // TODO 二进制跳表处理
                upR[k][i] = upR[k - 1][upR[k - 1][i]]
            }
        }

        fun hopRight(p: Int, target: Int): Int {
            if (reachR[p] < target) return -1
            var cur = p
            var steps = 0
            for (k in maxJump downTo 0) {
                val nx = upR[k][cur]
                if (nx < target) {
                    cur = nx
                    steps += (1 shl k)
                }
            }
            return steps + 1
        }

        return queries.map { (u, v) ->
            val pu = pos[u]
            val pv = pos[v]
            when {
                pu == pv -> 0
                pu < pv -> hopRight(pu, pv)
                else -> hopRight(pv, pu)
            }
        }.toIntArray()
    }
}