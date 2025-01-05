package leetcode.contest.c431

import utils.print
import utils.printLong
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionC()
    s.maximumCoins("[[8,10,1],[1,3,2],[5,6,4]]".toGrid(), 4).print()
    // 226
    s.maximumCoins("[[8,12,13],[29,32,2],[13,15,2],[40,41,18],[42,48,18],[33,36,11],[37,38,6]]".toGrid(), 28).print()
}

class SolutionC {
    fun maximumCoins(coins: Array<IntArray>, k: Int): Long {
        coins.sortBy { it[0] }
        val n = coins.size
        val pos = mutableListOf<Int>()

        // Collect positions based on boundaries
        for (i in coins.indices) {
            if (coins[i][0] + k - 1 < coins[n - 1][1]) {
                pos.add(coins[i][0])
            }
            if (coins[i][1] - k + 1 > coins[0][0]) {
                pos.add(coins[i][1] - k + 1)
            }
        }

        pos.add(coins[0][0])
        pos.add(coins[n - 1][1])
        pos.sort()
        pos.distinct()

        var p = 0
        var q = 0
        var v = 0L
        var head = 0L
        var tail = 0L
        var ans = 0L

        for (x in pos) {
            v -= head
            while (q < n && coins[q][1] < x + k) {
                v += (coins[q][1] - coins[q][0] + 1L) * coins[q][2]
                q++
            }
            head = if (q < n && coins[q][0] < x + k) {
                (x + k - coins[q][0]).toLong() * coins[q][2]
            } else {
                0L
            }
            v += head
            v += tail
            while (x > coins[p][1]) {
                v -= (coins[p][1] - coins[p][0] + 1L) * coins[p][2]
                p++
            }
            tail = if (x > coins[p][0]) {
                (x - coins[p][0]).toLong() * coins[p][2]
            } else {
                0L
            }
            v -= tail

            ans = maxOf(ans, v)
        }

        return ans
    }
}