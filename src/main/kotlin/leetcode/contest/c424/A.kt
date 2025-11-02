package leetcode.contest.c424

class SolutionA {
    fun countValidSelections(nums: IntArray): Int {
        val lSum = IntArray(nums.size)
        val rSum = IntArray(nums.size)

        var ans = 0

        var ls = 0
        var rs = 0

        for (i in 0 until nums.size) {
            ls += nums[i]
            lSum[i] = ls

            rs += nums[nums.size - i - 1]
            rSum[nums.size - i - 1] = rs
        }

        for (i in 0 until nums.size) {
            if (nums[i] == 0) {
                if (rSum[i] == lSum[i]) ans += 2
                else if (rSum[i] - lSum[i] == 1) ans += 1
                else if (lSum[i] - rSum[i] == 1) ans += 1
            }
        }

        return ans
    }
}