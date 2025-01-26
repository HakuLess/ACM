package leetcode.contest.c434

class SolutionA {
    fun countPartitions(nums: IntArray): Int {
        val n = nums.size
        val totalSum = nums.sum()
        var leftSum = 0
        var rightSum = totalSum
        var count = 0

        for (i in 0 until n - 1) {
            leftSum += nums[i]
            rightSum -= nums[i]

            if ((leftSum - rightSum) % 2 == 0) {
                count++
            }
        }

        return count
    }
}