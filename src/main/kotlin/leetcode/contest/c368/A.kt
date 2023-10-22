package leetcode.contest.c368

class SolutionA {
    fun minimumSum(nums: IntArray): Int {
        var ans = Int.MAX_VALUE
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                for (k in j + 1 until nums.size) {
                    if (nums[j] > nums[i] && nums[j] > nums[k]) {
                        ans = minOf(ans, nums[i] + nums[j] + nums[k])
                    }
                }
            }
        }
        return if (ans == Int.MAX_VALUE) -1 else ans
    }
}