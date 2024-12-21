package leetcode.contest.b146

class SolutionA {
    fun countSubarrays(nums: IntArray): Int {
        var count = 0
        for (i in 0 until nums.size - 2) {
            val first = nums[i]
            val second = nums[i + 1]
            val third = nums[i + 2]
            if ((first + third) * 2 == second) {
                count++
            }
        }
        return count
    }
}