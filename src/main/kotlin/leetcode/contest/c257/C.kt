package leetcode.contest.c257

import utils.print

fun main() {
    val s = Solution5865()
//    s.firstDayBeenInAllRooms(intArrayOf(0, 0)).print()
    s.firstDayBeenInAllRooms(intArrayOf(0, 0, 2)).print()
//    s.firstDayBeenInAllRooms(intArrayOf(0,1,2,0)).print()
}

class Solution5865 {
    fun firstDayBeenInAllRooms(nextVisit: IntArray): Int {
        val mod = 1000000007L
        val n = nextVisit.size
        var visit = 0
        val sum = (n - 1) * n / 2
        val cnt = BooleanArray(n) { false }
        var cur = 0
        var ans = 0
        println(sum)
        while (visit != sum) {
//            println("visit $cur")
            ans++
            val tmp = if (!cnt[cur]) {
                nextVisit[cur]
            } else {
                (cur + 1) % n
            }
            cnt[cur] = !cnt[cur]
            visit += cur
            cur = tmp
        }
        return ans
    }
}