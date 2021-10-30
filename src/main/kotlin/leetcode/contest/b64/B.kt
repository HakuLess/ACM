package leetcode.contest.b64

import utils.print
import utils.printInt
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionB()
    s.maxTwoEvents("[[1,3,2],[4,5,2],[2,4,3]]".toGrid()).print()
}

class SolutionB {
    fun maxTwoEvents(events: Array<IntArray>): Int {
        events.sortBy { it[1] }
        var cur = 0
        val left = TreeMap<Int, Int>()
        events.forEach {
            cur = maxOf(cur, it[2])
            // 该点左侧最大值
            left[it[1]] = cur
        }
        events.sortBy { it[0] }
        val right = TreeMap<Int, Int>()
        cur = 0
        events.reversed().forEach {
            cur = maxOf(cur, it[2])
            // 该点右侧最大值
            right[it[0] - 1] = cur
        }
        var ans = 0
        for (key in left.keys) {
            ans = if (right.ceilingKey(key) != null) {
                maxOf(ans, left[key]!! + right[right.ceilingKey(key)]!!)
            } else {
                maxOf(ans, left[key]!!)
            }
        }
        return ans
    }
}