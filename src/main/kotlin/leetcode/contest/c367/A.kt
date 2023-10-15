package leetcode.contest.c367

import kotlin.math.abs

class SolutionA {
    fun findIndices(nums: IntArray, indexDifference: Int, valueDifference: Int): IntArray {
        for (i in nums.indices) {
            for (j in i until nums.size) {
                if (j - i >= indexDifference && abs(nums[j] - nums[i]) >= valueDifference) {
                    return intArrayOf(i, j)
                }
            }
        }
        return intArrayOf(-1, -1)
    }
}