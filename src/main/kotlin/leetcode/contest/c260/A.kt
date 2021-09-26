package leetcode.contest.c260

class Solution5881 {
    fun maximumDifference(nums: IntArray): Int {
        var ans = -1
        for (i in 0 until nums.lastIndex) {
            for (j in i + 1 until nums.size) {
                if (nums[j] > nums[i])
                    ans = maxOf(ans, nums[j] - nums[i])
            }
        }
        return ans
    }
}