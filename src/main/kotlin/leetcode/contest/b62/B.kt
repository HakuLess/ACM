package leetcode.contest.b62

class Solution5872 {
    fun numOfPairs(nums: Array<String>, target: String): Int {
        var ans = 0
        for (i in nums.indices) {
            for (j in nums.indices) {
                if (i != j && nums[i] + nums[j] == target)
                    ans++
            }
        }
        return ans
    }
}