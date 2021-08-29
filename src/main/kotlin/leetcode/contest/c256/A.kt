package leetcode.contest.c256

class Solution5854 {
    fun minimumDifference(nums: IntArray, k: Int): Int {
        nums.sort()
        var ans = Int.MAX_VALUE
        for (i in k - 1 until nums.size) {
            ans = minOf(ans, nums[i] - nums[i - k + 1])
        }
        return ans
    }
}