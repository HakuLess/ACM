package leetcode.contest.c363

class SolutionA {
    fun sumIndicesWithKSetBits(nums: List<Int>, k: Int): Int {
        var ans = 0
        for (i in nums.indices) {
            if (i.toString(2).count { it == '1' } == k) {
                ans += nums[i]
            }
        }
        return ans
    }
}