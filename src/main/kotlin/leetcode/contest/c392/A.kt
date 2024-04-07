package leetcode.contest.c392

import utils.print

fun main() {
    val s = SolutionA()
    s.longestMonotonicSubarray(intArrayOf(1, 4, 3, 3, 2)).print()
}

class SolutionA {
    fun longestMonotonicSubarray(nums: IntArray): Int {
        val n = nums.size
        var a = 1
        var b = 1
        var ans = 1
        for (i in 0 until n - 1 ) {
            val j = i + 1
            if (nums[j] > nums[i]) {
                a++
            } else {
                a = 1
            }

            if (nums[j] < nums[i]) {
                b++
            } else {
                b = 1
            }
            ans = maxOf(ans, maxOf(a, b))
        }
        return ans
    }
}