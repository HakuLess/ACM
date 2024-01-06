package leetcode.contest.b121

class SolutionA {
    fun missingInteger(nums: IntArray): Int {
        var ans = 0
        for (i in nums.indices) {
            if (i == 0) {
                ans += nums[i]
            } else {
                if (nums[i] == nums[i - 1] + 1) {
                    ans+=nums[i]
                } else {
                    break
                }
            }
        }
        val set = nums.toHashSet()
        while (ans in set) {
            ans++
        }
        return ans
    }
}