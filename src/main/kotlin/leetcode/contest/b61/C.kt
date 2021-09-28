package leetcode.contest.b61

import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = Solution5861()
    s.maxTaxiEarnings(5, "[[2,5,4],[1,5,1]]".toGrid()).print()
    s.maxTaxiEarnings(20, "[[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]".toGrid()).print()
}

class Solution5861 {
    fun maxTaxiEarnings(n: Int, rides: Array<IntArray>): Long {
        val sorted = rides.sortedWith(compareBy { it[1] })
        val dp = LongArray(n + 1)
        var index = 0
        for (i in 1..n) {
            dp[i] = dp[i - 1]
            while (index in sorted.indices && sorted[index][1] == i) {
                dp[i] = maxOf(dp[i], dp[sorted[index][0]] + sorted[index][2] + sorted[index][1] - sorted[index][0])
                index++
            }
        }
        return dp.last()
    }

//    fun maxTaxiEarnings(n: Int, rides: Array<IntArray>): Long {
//        val sorted = rides.sortedWith(compareBy({ it[0] }, { -it[1] }, { -it[2] }))
//        val tm = TreeMap<Int, Long>()
//        for (it in sorted) {
//            val cur = (tm.floorEntry(it[0])?.value ?: 0) + it[2] + it[1] - it[0]
//            tm[it[1]] = maxOf(tm.getOrDefault(it[1], 0), cur)
//
//            if (cur != tm[it[1]]) continue
//            val pre = tm.floorEntry(it[1] - 1)
//            if (pre != null && pre.value >= cur) {
//                tm.remove(it[1])
//            }
//
//            var next = tm.ceilingEntry(it[1] + 1)
//            while (next != null) {
//                if (next.value < cur) {
//                    tm.remove(next.key)
//                } else {
//                    break
//                }
//                next = tm.ceilingEntry(next.key + 1)
//            }
//        }
//        return tm.values.maxOrNull()!!
////        return tm.values.max()!!
//    }
}