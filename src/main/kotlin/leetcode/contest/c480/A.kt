package leetcode.contest.c480

class SolutionA {
    fun absDifference(nums: IntArray, k: Int): Int {
        nums.sort()
        return nums.takeLast(k).sum() - nums.take(k).sum()
    }
}