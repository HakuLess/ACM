package leetcode.contest.c432

class SolutionD {
    fun countNonDecreasingSubarrays(nums: IntArray, k: Int): Long {
        val n = nums.size
        var count = 0L
        for (i in 0 until n) {
            var cost = 0
            var max = nums[i]

            for (j in i until n) {
                // 检查当前元素是否小于最大值
                if (nums[j] < max) {
                    cost += max - nums[j]
                } else {
                    max = nums[j]
                }
                // 添加计数，如果成本不超过k
                if (cost <= k) {
                    count++
                } else {
                    break
                }
            }
        }
        return count
    }
}