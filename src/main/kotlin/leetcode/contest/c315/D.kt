package leetcode.contest.c315

import utils.print

fun main() {
    val s = SolutionD()
//    s.countSubarrays(intArrayOf(1, 3, 5, 2, 7, 5), 1, 5).print()
//    s.countSubarrays(intArrayOf(1, 1, 1, 1), 1, 1).print()
    // 8
    s.countSubarrays(intArrayOf(1, 1, 2, 1, 1), 1, 2).print()
}

class SolutionD {
    fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
        var ans = 0L
        var left = 0
        var minPos = -1
        var maxPos = -1
        for (right in nums.indices) {
            if (nums[right] == minK)
                minPos = right
            if (nums[right] == maxK)
                maxPos = right
            if ((nums[right] < minK) || (nums[right] > maxK)) {
                left = right + 1
            }
            ans += maxOf(0, minOf(minPos, maxPos) - left + 1)
        }
        return ans
    }
}