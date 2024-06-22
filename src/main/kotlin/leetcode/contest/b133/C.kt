package leetcode.contest.b133

class SolutionC {
    fun minOperations(nums: IntArray): Int {
        var ans = 0
        var target = 0
        for (i in nums.indices) {
            if (nums[i] == target) {
                ans++
                target = 1 - target
            }
        }
        return ans
    }
}