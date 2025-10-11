package leetcode.contest.b167

class SolutionB {
    fun longestSubarray(nums: IntArray): Int {
        var ans = 0
        var tmp = 2
        for (i in nums.indices) {
            if (i < 2) {
                ans = maxOf(ans, i + 1)
            } else {
                if (nums[i] == nums[i - 1] + nums[i - 2]) {
                    tmp++
                    ans = maxOf(ans, tmp)
                } else {
                    tmp = 2
                }
            }
        }
        return ans
    }
}