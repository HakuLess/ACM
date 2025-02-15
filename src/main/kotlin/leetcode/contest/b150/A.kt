package leetcode.contest.b150

class SolutionA {
    fun sumOfGoodNumbers(nums: IntArray, k: Int): Int {
        var sum = 0
        for (i in nums.indices) {
            var isGood = true
            if (i - k >= 0 && nums[i] <= nums[i - k]) {
                isGood = false
            }
            if (i + k < nums.size && nums[i] <= nums[i + k]) {
                isGood = false
            }
            if (isGood) {
                sum += nums[i]
            }
        }
        return sum
    }
}