package leetcode.contest.c446

class SolutionB {
    fun maximumPossibleSize(nums: IntArray): Int {
        var c = nums[0]
        var ans = 0
        for (i in nums.indices) {
            if (c <= nums[i]) {
                ans++
                c = maxOf(c, nums[i])
            }
        }
        return ans
    }
}