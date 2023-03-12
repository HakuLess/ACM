package leetcode.contest.b98

import utils.*

fun main() {
    val s = SolutionD()
    // [3]
//    s.handleQuery(intArrayOf(1, 0, 1), intArrayOf(0, 0, 0), "[[1,1,1],[2,1,0],[3,0,0]]".toGrid()).print()
//    s.handleQuery(intArrayOf(1), intArrayOf(5), "[[2,0,0],[3,0,0]]".toGrid()).print()
    // [679,679,1053]
    s.handleQuery(
        intArrayOf(0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1),
        intArrayOf(30, 46, 43, 34, 39, 16, 14, 41, 22, 11, 32, 2, 44, 12, 22, 36, 44, 49, 50, 10, 33, 7, 42),
        "[[1,15,21],[3,0,0],[3,0,0],[2,21,0],[2,13,0],[3,0,0]]".toGrid()
    ).print()

    // [2]
    s.handleQuery(
        intArrayOf(1),
        intArrayOf(1),
        "[[2,1,0],[3,0,0]]".toGrid()
    ).print()
}

class SolutionD {
    fun handleQuery(nums1: IntArray, nums2: IntArray, queries: Array<IntArray>): LongArray {
        val n = nums1.size
        val ans = ArrayList<Long>()
        var curr = 0L
        nums2.forEach {
            curr += it
        }
        val st = SegmentTreeGPT(LongArray(n))
        for (i in nums1.indices) {
            if (nums1[i] == 1) {
                st.updateAdd(i, i, 1)
            }
        }
        for ((x, y, z) in queries) {
//            for (i in 0..n - 1) {
//                println("cur $i: ${st.querySum(i, i)}")
//            }
            when (x) {
                1 -> {
                    st.updateMul(y, z, -1)
                    st.updateAdd(y, z, 1)
                }
                2 -> {
                    curr += y * st.querySum(0, n - 1)
                }
                else -> ans.add(curr)
            }
        }
        return ans.toLongArray()
    }
}