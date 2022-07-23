package leetcode.contest.b83

class SolutionB {
    fun zeroFilledSubarray(nums: IntArray): Long {
        var ans = 0L
        var cur = 0L
        for (i in nums.indices) {
            if (nums[i] == 0) {
                cur++
            } else {
                ans += cur * (cur + 1) / 2
                cur = 0L
            }
        }
        ans += cur * (cur + 1) / 2
        return ans
    }
}