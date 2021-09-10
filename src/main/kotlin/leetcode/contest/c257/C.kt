package leetcode.contest.c257

import utils.print

fun main() {
    val s = Solution5865()
//    s.firstDayBeenInAllRooms(intArrayOf(0, 0)).print()
//    s.firstDayBeenInAllRooms(intArrayOf(0, 0, 2)).print()
    s.firstDayBeenInAllRooms(intArrayOf(0, 1, 2, 0)).print()
}

class Solution5865 {
    fun firstDayBeenInAllRooms(nextVisit: IntArray): Int {
        val mod = 1000000007L
        val n = nextVisit.size
        val dp = LongArray(n) { 0 }
        for (i in 1 until n) {
            dp[i] = (2 * dp[i - 1] - dp[nextVisit[i - 1]] + 2 + mod) % mod
        }
        return dp.last().toInt()
    }
}