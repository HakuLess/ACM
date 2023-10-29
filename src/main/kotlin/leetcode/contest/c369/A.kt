package leetcode.contest.c369

class SolutionA {
    fun findKOr(nums: IntArray, k: Int): Int {
        var ans = 0
        var step = 1
        repeat(31) {
            val c = nums.count { it and step != 0 }
            if (c >= k) {
                ans += step
            }
            step *= 2
        }
        return ans
    }
}