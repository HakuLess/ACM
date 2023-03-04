package leetcode.contest.b99

import utils.print
import utils.quickPower
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.countWays("[[6,10],[5,15]]".toGrid()).print()
    s.countWays("[[1,3],[10,20],[2,5],[4,8]]".toGrid()).print()
}

class SolutionC {
    fun countWays(ranges: Array<IntArray>): Int {
        val mod = 1000000007L
        ranges.sortWith(compareBy({ it[0] }, { -it[1] }))
        var pre = -1
        var cur = 0
        for (i in ranges.indices) {
            val item = ranges[i]
            if (item[0] > pre) {
//                println("pre is $pre cmp ${item[1]}")
                cur++
            }
            pre = maxOf(pre, item[1])
        }
        return quickPower(2L, cur.toLong(), mod).toInt()
    }
}