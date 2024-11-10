package leetcode.contest.c423

class SolutionA {
    fun hasIncreasingSubarrays(nums: List<Int>, k: Int): Boolean {
        val n = nums.size

        for (i in 0 until n - 2 * k + 1) {
            var isFirstIncreasing = true
            var isSecondIncreasing = true

            for (j in i until i + k - 1) {
                if (nums[j] >= nums[j + 1]) {
                    isFirstIncreasing = false
                    break
                }
            }

            for (j in i + k until i + 2 * k - 1) {
                if (nums[j] >= nums[j + 1]) {
                    isSecondIncreasing = false
                    break
                }
            }

            if (isFirstIncreasing && isSecondIncreasing) {
                return true
            }
        }

        return false
    }
}