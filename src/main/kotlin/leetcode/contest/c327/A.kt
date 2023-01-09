package leetcode.contest.c327

class SolutionA {
    fun maximumCount(nums: IntArray): Int {
        return maxOf(nums.count { it > 0 }, nums.count { it < 0 })
    }
}