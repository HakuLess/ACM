package leetcode.contest.c355

import utils.print
import java.util.*


fun main() {
    val s = SolutionC()
//    s.maxIncreasingGroups(listOf(1, 2, 5)).print()
    s.maxIncreasingGroups(listOf(2, 2, 2)).print()
    s.maxIncreasingGroups(listOf(2, 1, 2)).print()
    s.maxIncreasingGroups(listOf(5, 3, 5, 5)).print()
}

class SolutionC {
    fun maxIncreasingGroups(usageLimits: List<Int>): Int {
        val l = usageLimits.toIntArray()
        l.sort()
        var cur = 0L
        var ans = 0L
        l.forEach {
            cur += it
            if (cur >= ans + 1) {
                ans += 1
                cur -= ans
            }
        }
        return ans.toInt()
    }
}