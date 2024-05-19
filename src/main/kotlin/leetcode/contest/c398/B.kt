package leetcode.contest.c398

import utils.print
import utils.toGrid
import kotlin.math.abs

fun main() {
    val s = SolutionB()
    s.isArraySpecial(intArrayOf(4, 3, 1, 6), "[[0,2],[2,3]]".toGrid()).print()
    s.isArraySpecial(intArrayOf(2, 8, 10, 9), "[[1,3]]".toGrid()).print()
}

class SolutionB {
    fun isArraySpecial(nums: IntArray, queries: Array<IntArray>): BooleanArray {
        val l = ArrayList<Int>()
        for (i in nums.indices) {
            if (i == 0) continue
            if (abs(nums[i] - nums[i - 1]) % 2 == 0) {
                // 包含i，则不是特殊数组
                l.add(i)
            }
        }

        l.joinToString().print()

        // 从小到大排序
        val n = queries.size
        val ids = IntRange(0, n - 1).toList().toTypedArray()
        ids.sortBy { queries[it][0] }
        queries.sortBy { it[0] }

        val ans = BooleanArray(n)
        var i = 0
        queries.forEachIndexed { index, item ->
            while (i in l.indices && l[i] < item[0]) {
                i++
            }

            println("bound is ${item.joinToString()} with ${l[i]}")

            ans[ids[index]] = if (i in l.indices && item[0] < l[i] && item[1] >= l[i]) {
                false
            } else if (i in l.indices && item[0] == l[i] && i + 1 in l.indices && item[1] >= l[i + 1]) {
                false
            } else {
                true
            }
        }
        return ans
    }
}