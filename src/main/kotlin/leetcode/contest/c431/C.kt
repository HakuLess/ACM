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
        var res = 0L

        // Left segment
        var left = 0
        var right = 0
        var cur = 0L
        while (left < coins.size) {
            while (right < coins.size && coins[right][1] - coins[left][0] < k) {
                cur += (coins[right][1] - coins[right][0] + 1L) * coins[right][2]
                right++
            }
            if (right < coins.size && coins[right][1] - coins[left][0] >= k && coins[right][0] - coins[left][0] < k) {
                res = maxOf(res, cur + (coins[left][0] + k - coins[right][0]) * coins[right][2])
            } else {
                res = maxOf(res, cur)
            }
            if (coins[left][1] - coins[left][0] < k) {
                cur -= (coins[left][1] - coins[left][0] + 1L) * coins[left][2]
            }
            left++
            right = maxOf(right, left)
        }

        // Right segment
        left = coins.size - 1
        right = coins.size - 1
        cur = 0L
        while (right >= 0) {
            while (left >= 0 && coins[right][1] - coins[left][0] < k) {
                cur += (coins[left][1] - coins[left][0] + 1L) * coins[left][2]
                left--
            }
            if (left >= 0 && coins[right][1] - coins[left][0] >= k && coins[right][1] - coins[left][1] < k) {
                res = maxOf(res, cur + (coins[left][1] - coins[right][1] + k) * coins[left][2])
            } else {
                res = maxOf(res, cur)
            }
            if (coins[right][1] - coins[right][0] < k) {
                cur -= (coins[right][1] - coins[right][0] + 1L) * coins[right][2]
            }
            right--
            left = minOf(left, right)
        }

        return res
    }
}