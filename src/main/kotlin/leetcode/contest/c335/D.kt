package leetcode.contest.c335

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.waysToReachTarget(6, "[[6,1],[3,2],[2,3]]".toGrid()).print()
}

class SolutionD {
    fun waysToReachTarget(target: Int, types: Array<IntArray>): Int {

        val mod = 1000000007L

        val ans = LongArray(1001)
        ans[0] = 1
        types.forEach {
            val (count, mark) = it
            for (i in ans.indices.reversed()) {
                for (c in 1..count) {
                    if (ans[i] != 0L && i + c * mark in ans.indices) {
                        ans[i + c * mark] += ans[i]
                        ans[i + c * mark] %= mod
                    }
                }
            }
        }

        return ans[target].toInt()
    }
}