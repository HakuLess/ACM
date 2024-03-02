package leetcode.contest.b125

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    // 260
    s.maximumValueSum(intArrayOf(24, 78, 1, 97, 44), 6, "[[0,2],[1,2],[4,2],[3,4]]".toGrid()).print()
}

class SolutionD {
    fun maximumValueSum(nums: IntArray, k: Int, edges: Array<IntArray>): Long {
        var ans = 0L
        for (i in nums.indices) {
            ans += nums[i]
        }
        val sorted = nums.map { (it xor k) - it }.sortedDescending()
        val n = nums.size
        for (i in 1 until n step 2) {
            ans = maxOf(ans, ans + sorted[i] + sorted[i - 1])
        }
        return ans
    }
}