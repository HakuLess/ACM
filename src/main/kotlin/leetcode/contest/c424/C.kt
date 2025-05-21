package leetcode.contest.c424

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.minZeroArray(
        intArrayOf(0, 8),
        "[[0,1,4],[0,1,1],[0,1,4],[0,1,1],[1,1,5],[0,1,2],[1,1,4],[0,1,1],[1,1,3],[0,0,2],[1,1,3],[1,1,2],[0,1,5],[1,1,2],[1,1,5]]".toGrid()
    ).print()
}

class SolutionC {
    fun minZeroArray(nums: IntArray, queries: Array<IntArray>): Int {
        val n = nums.size
        val delta = IntArray(n + 1)

        var k = 0
        var cur = 0
        for (i in nums.indices) {
            val num = nums[i]
            cur += delta[i]

            while (k in queries.indices && cur < num) {
                val (l, r, diff) = queries[k]
                delta[l] += diff
                delta[r + 1] -= diff

                if (i in l..r) {
                    cur += diff
                }
                k++
            }

            if (cur < num) return -1
        }

        return k
    }
}