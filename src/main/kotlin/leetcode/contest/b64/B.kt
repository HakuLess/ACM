package leetcode.contest.b64

import utils.print
import utils.printInt
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = Solution2054()
    s.maxTwoEvents("[[1,3,2],[4,5,2],[2,4,3]]".toGrid()).print()
}

// 最多选择两个不重叠的Events，最高价值
class Solution2054 {
    fun maxTwoEvents(events: Array<IntArray>): Int {
        // 按照时间进行排序
        val arr = ArrayList<IntArray>()
        events.forEach {
            arr.add(intArrayOf(1, it[0], it[2]))
            arr.add(intArrayOf(-1, it[1], it[2]))
        }
        arr.sortWith(compareBy({ it[1] }, { -it[0] }))

        var preMax = 0
        var ans = 0
        arr.forEach {
            if (it[0] == 1) {
                ans = maxOf(ans, it[2] + preMax)
            } else {
                preMax = maxOf(preMax, it[2])
            }
        }
        return ans
    }
}