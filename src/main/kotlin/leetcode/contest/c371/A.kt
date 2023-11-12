package leetcode.contest.c371

import kotlin.math.abs

class SolutionA {
    fun maximumStrongPairXor(nums: IntArray): Int {
        var ans = 0
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                val x = nums[i]
                val y = nums[j]
                if (abs(x - y) <= minOf(x, y)) {
                    ans = maxOf(ans, x xor y)
                }
            }
        }
        return ans
    }
}