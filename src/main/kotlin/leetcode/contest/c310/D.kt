package leetcode.contest.c310

import utils.SegmentTree
import utils.print

fun main() {
    val s = SolutionD()
    // 5
    s.lengthOfLIS(intArrayOf(4, 2, 1, 4, 3, 4, 5, 8, 15), 3).print()

    // 4
    s.lengthOfLIS(intArrayOf(1, 100, 500, 100000, 100000), 100000).print()
}

class SolutionD {
    fun lengthOfLIS(nums: IntArray, k: Int): Int {
        val rootMax = SegmentTree<Long>(start = 0, end = Int.MAX_VALUE / 4, value = 0L) { a, b ->
            maxOf(a, b)
        }
        nums.forEach {
            val q = rootMax.query(rootMax, it - k, it - 1)
            rootMax.update(rootMax, it, q + 1)
        }
        return rootMax.query(rootMax, 0, Int.MAX_VALUE / 4).toInt()
    }
}