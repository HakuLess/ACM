package leetcode.contest.c265

class SolutionA {
    fun smallestEqual(nums: IntArray): Int {
        for (i in nums.indices) {
            if (i % 10 == nums[i])
                return i
        }
        return -1
    }
}