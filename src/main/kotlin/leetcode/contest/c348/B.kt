package leetcode.contest.c348

class SolutionB {
    fun semiOrderedPermutation(nums: IntArray): Int {
        val n = nums.size
        val a = nums.indexOf(1)
        val b = nums.indexOf(n)
        var ans = a + (n - b - 1)
        if (a > b) ans--
        return ans
    }
}