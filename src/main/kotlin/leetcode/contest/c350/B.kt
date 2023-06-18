package leetcode.contest.c350

class SolutionB {
    fun findValueOfPartition(nums: IntArray): Int {
        var ans = Int.MAX_VALUE
        nums.sort()
        for (i in 1 until nums.size) {
            ans = minOf(ans, nums[i] - nums[i - 1])
        }
        return ans
    }
}