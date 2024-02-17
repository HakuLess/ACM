package leetcode.contest.b124

class SolutionA {
    fun maxOperations(nums: IntArray): Int {
        if (nums.size < 2) return 0
        val sum = nums[0] + nums[1]
        var ans = 0
        for (i in nums.indices step 2) {
            if (i + 1 in nums.indices && nums[i] + nums[i + 1] == sum) {
                ans++
            } else {
                return ans
            }
        }
        return ans
    }
}