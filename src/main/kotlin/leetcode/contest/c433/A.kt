package leetcode.contest.c433

class SolutionA {
    fun subarraySum(nums: IntArray): Int {
        var totalSum = 0
        for (i in nums.indices) {
            val start = maxOf(0, i - nums[i])
            for (j in start..i) {
                totalSum += nums[j]
            }
        }
        return totalSum
    }
}