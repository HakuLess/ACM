package leetcode.contest.b114

import utils.print

fun main() {
    val s = SolutionC()
    s.maxSubarrays(intArrayOf(1, 0, 2, 0, 1, 2)).print()
}

class SolutionC {
    fun maxSubarrays(nums: IntArray): Int {
        var c = nums[0]
        for (i in 1 until nums.size) {
            c = c and nums[i]
        }
        if (c != 0) return 1
        c = nums[0]
        var ans = 0
        for (i in nums.indices) {
            c = c and nums[i]
            if (c == 0) {
                ans++
                if (i + 1 in nums.indices)
                    c = nums[i + 1]
            } else {
                c = c and nums[i]
            }
        }
        return ans
    }
}