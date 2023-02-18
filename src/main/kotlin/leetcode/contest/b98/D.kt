package leetcode.contest.b98

import utils.BitTree
import utils.SegmentTree
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
//    s.handleQuery(intArrayOf(1, 0, 1), intArrayOf(0, 0, 0), "[[1,1,1],[2,1,0],[3,0,0]]".toGrid()).print()
//    s.handleQuery(intArrayOf(1), intArrayOf(5), "[[2,0,0],[3,0,0]]".toGrid()).print()
    // [679,679,1053]
    s.handleQuery(
        intArrayOf(0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1),
        intArrayOf(30, 46, 43, 34, 39, 16, 14, 41, 22, 11, 32, 2, 44, 12, 22, 36, 44, 49, 50, 10, 33, 7, 42),
        "[[1,15,21],[3,0,0],[3,0,0],[2,21,0],[2,13,0],[3,0,0]]".toGrid()
    ).print()
}

// TODO 30 线段树 乘积
class SolutionD {
    fun handleQuery(nums1: IntArray, nums2: IntArray, queries: Array<IntArray>): LongArray {

        var sum = 0L
        nums2.forEach {
            sum += it
        }
        val ans = ArrayList<Long>()
        queries.forEach { item ->
            when (item[0]) {
                1 -> {
                    for (i in item[1]..item[2]) {
                        nums1[i] = 1 - nums1[i]
                    }
                }
                2 -> {
                    val left = nums1.sum().toLong()
                    sum += left * item[1]
                }
                3 -> {
                    ans.add(sum)
                }
            }
        }
        return ans.toLongArray()
    }
}