package leetcode.contest.c391

class SolutionC {
    fun countAlternatingSubarrays(nums: IntArray): Long {
        var cnt = 1L
        var ans = 0L

        for (i in 1 until nums.size) {
            if (nums[i] != nums[i - 1]) {
                cnt++
            } else {
                ans += cnt * (cnt + 1) / 2
                cnt = 1
            }
        }

        ans += cnt * (cnt + 1) / 2
        return ans
    }
}