package leetcode.contest.c310

import utils.print
import utils.printInt
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionC()
    s.minGroups("[[5,10],[6,8],[1,5],[2,3],[1,10]]".toGrid()).print()
}

class SolutionC {
    fun minGroups(intervals: Array<IntArray>): Int {
        val tm = TreeMap<Int, Int>()
        intervals.sortBy { it[0] }
        intervals.forEach {
            val k = tm.floorEntry(it[0] - 1)
            if (k == null) {
                tm[it[1]] = tm.getOrDefault(it[1], 0) + 1
            } else {
                if (k.value == 1) {
                    tm.remove(k.key)
                } else {
                    tm[k.key] = tm[k.key]!! - 1
                }
                tm[it[1]] = tm.getOrDefault(it[1], 0) + 1
            }
//            tm.printInt()
        }
        var ans = 0
        tm.forEach { t, u ->
            ans += u
        }
        return ans
    }
}