package leetcode.contest.b87

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.minimumMoney("[[2,1],[5,0],[4,2]]".toGrid()).print()
}

class SolutionD {
    fun minimumMoney(transactions: Array<IntArray>): Long {
        val n = transactions.size
        val cost = LongArray(n)
        var maxCost = 0L
        for (i in transactions.indices) {
            cost[i] = (transactions[i][1] - transactions[i][0]).toLong()
            if (cost[i] < 0) {
                maxCost += cost[i]
            }
        }
        var ans = 0L
        for (i in transactions.indices) {
            val c = if (cost[i] < 0) {
                maxCost - cost[i]
            } else {
                maxCost
            }
            ans = maxOf(ans, transactions[i][0] - c)
        }
        return ans
    }
}