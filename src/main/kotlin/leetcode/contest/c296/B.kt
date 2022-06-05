package leetcode.contest.c296

class SolutionB {
    fun partitionArray(nums: IntArray, k: Int): Int {
        nums.sort()
        var first: Int? = null
        var ans = 1
        for (i in nums.indices) {
            if (first == null) {
                first = nums[i]
            } else if (nums[i] - first > k) {
                first = nums[i]
                ans++
            }
        }
        return ans
    }
}