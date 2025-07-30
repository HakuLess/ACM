package leetcode.contest.c312

import utils.print

fun main() {
    val s = SolutionB()
    s.longestSubarray(intArrayOf(1, 2, 3, 3, 2, 2)).print()
}

class SolutionB {
    fun longestSubarray(nums: IntArray): Int {
        var maxValue = nums[0]
        var ans = 1
        var cnt = 1
        for (i in 1 until nums.size) {
            if (nums[i] > maxValue) {
                ans = 1
                cnt = 1
                maxValue = nums[i]
            } else if (nums[i] < maxValue) {
                cnt = 0
            } else if (nums[i] == maxValue) {
                cnt++
            }
            ans = maxOf(cnt, ans)
        }
        return ans
    }
}