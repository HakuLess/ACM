package leetcode.contest.b144

import utils.SegmentTree
import utils.print
import utils.segment.SegmentTreeGPTMinus
import utils.toGrid

fun main() {
    val s = SolutionC()
    // 4
    s.maxRemoval(intArrayOf(1, 2), "[[1,1],[0,0],[1,1],[1,1],[0,1],[0,0]]".toGrid()).print()
    // 8
    s.maxRemoval(
        intArrayOf(0, 4, 4, 0),
        "[[0,0],[3,3],[0,2],[3,3],[0,2],[0,1],[1,1],[1,2],[0,1],[2,3],[0,1],[1,2]]".toGrid()
    ).print()
}

class SolutionC {
    fun maxRemoval(nums: IntArray, queries: Array<IntArray>): Int {

        val n = nums.size

        val root = SegmentTree<Int>(start = 0, end = nums.size + 1, value = 0) { a, b -> a + b }
        for (i in nums.indices) {
            if (nums[i] > 0) {
                root.update(root, i, i, 1)
            }
        }
        queries.sortWith(compareBy { root.query(root, it[0], it[1]) })

        val segmentTreeGPTMinus = SegmentTreeGPTMinus(n)

        for (i in nums.indices) {
            segmentTreeGPTMinus.rangeAdd(i, i, nums[i])
        }
        for (i in queries.indices) {
            val (l, r) = queries[i]
            segmentTreeGPTMinus.rangeAdd(l, r, -1)
        }

        for (i in nums.indices) {
            val v = segmentTreeGPTMinus.query(i, i)
            segmentTreeGPTMinus.rangeAdd(i, i, -v * 2)
        }

        if (segmentTreeGPTMinus.query(0, n) < 0) return -1

        var ans = 0
        for (i in queries.indices) {
            val (l, r) = queries[i]
            segmentTreeGPTMinus.rangeAdd(l, r, -1)

            if (segmentTreeGPTMinus.query(0, n - 1) >= 0) {
                ans++
            } else {
                segmentTreeGPTMinus.rangeAdd(l, r, 1)
            }
        }

        return ans
    }
}