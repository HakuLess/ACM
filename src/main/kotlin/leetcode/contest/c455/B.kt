package leetcode.contest.c455

import utils.print
import utils.toArrayList

fun main() {
    val s = SolutionB()
    s.findCoins(intArrayOf(0, 1, 0, 2, 0, 3, 0, 4, 0, 5)).joinToString().print()
    s.findCoins(intArrayOf(1, 2, 2, 3, 4)).joinToString().print()
    s.findCoins(intArrayOf(1, 2, 3, 4, 15)).joinToString().print()
    s.findCoins(intArrayOf(96, 2)).joinToString().print()
}

class SolutionB {
    fun findCoins(numWays: IntArray): List<Int> {
        val n = numWays.size
        val dp = IntArray(n + 1)
        dp[0] = 1
        val ways = ArrayList<Int>()
        ways.add(0)
        ways.addAll(numWays.toTypedArray())
        val ans = ArrayList<Int>()

        for (i in 1 until ways.size) {
//            dp.print()
//            println("$i: ${dp[i]} cmp ${ways[i]} add $i")
            if (dp[i] < ways[i]) {
//                println("add $i: ${dp[i]} cmp ${ways[i]}")
                ans.add(i)
                for (j in i..n) {
                    dp[j] += dp[j - i]
                }
            } else if (dp[i] > ways[i]) {
                return emptyList()
            }
        }

        val l = ArrayList<Int>()
        l.addAll(dp.toTypedArray())
        l.removeFirst()
        if (l.joinToString() != numWays.joinToString()) return emptyList()
        return ans
    }
}