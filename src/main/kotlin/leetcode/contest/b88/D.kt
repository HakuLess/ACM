package leetcode.contest.b88

import utils.SegmentTree
import utils.print

fun main() {
    val s = SolutionD()
    s.numberOfPairs(intArrayOf(3, 2, 5), intArrayOf(2, 2, 1), 1).print()
    s.numberOfPairs(intArrayOf(3, -1), intArrayOf(-2, 2), -1).print()
}

class SolutionD {
    fun numberOfPairs(nums1: IntArray, nums2: IntArray, diff: Int): Long {
        val offset = 1000000
        val d = IntArray(nums1.size)
        for (i in nums1.indices) {
            d[i] = nums1[i] - nums2[i] + offset
        }

        var ans = 0L
        val root = SegmentTree<Int>(start = 0, end = Int.MAX_VALUE / 2, value = 0) { a, b -> a + b }
        for (i in d.indices) {
            ans += root.query(root, 0, d[i] + diff)
            val cur = root.query(root, d[i], d[i])
            root.update(root, d[i], cur + 1)
        }
        return ans
    }
}