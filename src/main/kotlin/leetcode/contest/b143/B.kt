package leetcode.contest.b143

import utils.SegmentTree
import utils.SegmentTreeGPT
import utils.print

fun main() {
    val s = SolutionB()
    s.maxFrequency(intArrayOf(88, 53), 27, 2).print()
    s.maxFrequency(intArrayOf(1, 4, 5), 1, 2).print()
    s.maxFrequency(intArrayOf(5, 11, 20, 20), 5, 1).print()
    s.maxFrequency(intArrayOf(14, 67, 36, 118, 4), 62, 3).print()
}

class SolutionB {
    fun maxFrequency(nums: IntArray, k: Int, numOperations: Int): Int {
        val n = 100005
        val min = nums.minOrNull()!!
        val max = nums.maxOrNull()!!
        val st = SegmentTreeGPT(LongArray(n))
        nums.forEach {
            st.updateAdd(it, it, 1)
        }

        var ans = 0
        for (i in min..max) {
            val item = st.querySum(i, i).toInt()
            ans = maxOf(ans, minOf(item + numOperations, st.querySum(i - k, i + k).toInt()))
        }
        return ans
    }
}