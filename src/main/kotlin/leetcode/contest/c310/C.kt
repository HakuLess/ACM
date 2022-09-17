package leetcode.contest.c310

import utils.print
import utils.printInt
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionC()
    s.minGroups("[[5,10],[6,8],[1,5],[2,3],[1,10]]".toGrid()).print()
}

// 差分数组
class SolutionC {
    fun minGroups(intervals: Array<IntArray>): Int {
        val arr = IntArray(1000005)
        intervals.forEach {
            arr[it[0]]++
            arr[it[1] + 1]--
        }
        var ans = 0
        for (i in 1 until arr.size) {
            arr[i] += arr[i - 1]
            ans = maxOf(ans, arr[i])
        }
        return ans
    }
}