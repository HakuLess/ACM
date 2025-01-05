package leetcode.contest.c431

import utils.gcd
import utils.lcm

class SolutionA {
    fun maxLength(nums: IntArray): Int {
        var maxLength = 0

        for (start in nums.indices) {
            var currentGcd = nums[start]
            var currentLcm = nums[start]
            var product = nums[start].toLong()

            for (end in start until nums.size) {
                if (end > start) {
                    currentGcd = gcd(currentGcd, nums[end])
                    currentLcm = lcm(currentLcm, nums[end])
                    product *= nums[end]
                }

                if (product == (currentGcd.toLong() * currentLcm)) {
                    maxLength = maxOf(maxLength, end - start + 1)
                }
            }
        }

        return maxLength
    }
}