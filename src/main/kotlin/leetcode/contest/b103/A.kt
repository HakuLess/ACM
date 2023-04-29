package leetcode.contest.b103

class SolutionA {
    fun maximizeSum(nums: IntArray, k: Int): Int {
//        val max = nums.max()!!
        val max = nums.maxOrNull()!!
        return (max + (max + k - 1)) * k / 2
    }
}