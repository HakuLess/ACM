package leetcode.contest.c384

import utils.print

fun main() {
    val s = SolutionB()
    s.countMatchingSubarrays(intArrayOf(1, 2, 3, 4, 5, 6), intArrayOf(1, 1)).print()
}

class SolutionB {
    fun countMatchingSubarrays(nums: IntArray, pattern: IntArray): Int {
        val n = pattern.size + 1
        var ans = 0
        for (i in 0..nums.size - n) {
//            println("enter $i with ${i + n - 1}")
            var valid = true
            for (j in 1 until n) {
//                println("compare ${i + j} with ${i + j - 1}")
                valid = when (pattern[j - 1]) {
                    1 -> nums[i + j] > nums[i + j - 1]
                    0 -> nums[i + j] == nums[i + j - 1]
                    else -> nums[i + j] < nums[i + j - 1]
                }
                if (!valid) break
            }
            if (valid) ans++
        }
        return ans
    }
}