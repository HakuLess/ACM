package leetcode.contest.b108

import utils.print

fun main() {
    val s = SolutionA()
    s.alternatingSubarray(intArrayOf(4, 5, 6)).print()
}

class SolutionA {
    fun alternatingSubarray(nums: IntArray): Int {
        var ans = -1
        for (i in nums.indices) {
            var j = i + 1
            while (j in nums.indices) {
                val minus = (j - i) % 2 == 0
                println("$j - $i with $minus")
                if (nums[j] == nums[j - 1] + (if (minus) -1 else 1)) {
                    j++
                } else {
                    break
                }
                ans = maxOf(ans, j - i)
            }
        }
        return ans
    }
}