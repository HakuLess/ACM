package leetcode.contest.b146

class SolutionA {
    fun countSubarrays(nums: IntArray): Int {
        var ans = 0
        for (i in 0 until nums.size - 2) {
            val a = nums[i]
            val b = nums[i + 1]
            val c = nums[i + 2]
            if ((a + c) * 2 == b) {
                ans++
            }
        }
        return ans
    }
}