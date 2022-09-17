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
        val root = SegmentTree<Long>(start = 0, end = 1000005, value = 0L) { a, b ->
            maxOf(a, b)
        }
        nums.forEach {
            val q = root.query(root, it - k, it - 1)
            root.update(root, it, q + 1)
        }
        return root.query(root, 0, 1000005).toInt()
    }
}