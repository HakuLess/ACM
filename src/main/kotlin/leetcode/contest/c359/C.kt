package leetcode.contest.c359

import utils.print
import java.util.*

fun main() {
    val s = SolutionC()
    // 10
    s.maximizeTheProfit(5, listOf(listOf(0, 0, 1), listOf(0, 2, 10), listOf(1, 3, 2))).print()
}

class SolutionC {
    fun maximizeTheProfit(n: Int, offers: List<List<Int>>): Int {
        val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.second })
        offers.forEach {
            pq.offer(Triple(it[0], it[1], it[2]))
        }
        val dp = IntArray(n)
        for (i in dp.indices) {
            if (i > 0) dp[i] = dp[i - 1]

            while (pq.isNotEmpty() && pq.peek().second == i) {
                val (left, right, v) = pq.poll()
                dp[right] = maxOf(dp[right], if (left == 0) v else dp[left - 1] + v)
            }
        }
        dp.print()
        return dp.last()
    }
}