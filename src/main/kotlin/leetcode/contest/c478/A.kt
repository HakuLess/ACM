package leetcode.contest.c478

import utils.print

fun main() {
    val s = SolutionA()
    s.countElements(intArrayOf(5, 5, 5), 2).print()
}

class SolutionA {
    fun countElements(nums: IntArray, k: Int): Int {
        val n = nums.size
        if (k == 0) return n

        nums.sort()
        val threshold = nums[n - k]

        var ans = 0
        for (i in 0 until n - k) {
            if (nums[i] < threshold) ans++
        }
        return ans
    }
}