package leetcode.contest.c305

class SolutionA {
    fun arithmeticTriplets(nums: IntArray, diff: Int): Int {
        var ans = 0
        for (i in 0 until nums.size - 2) {
            for (j in i + 1 until nums.size - 1) {
                for (k in j + 1 until nums.size) {
                    if (nums[k] - nums[j] == diff && nums[j] - nums[i] == diff) {
                        ans++
                    }
                }
            }
        }
        return ans
    }
}