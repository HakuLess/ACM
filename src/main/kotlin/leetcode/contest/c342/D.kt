package leetcode.contest.c342

import utils.gcd
import utils.print

fun main() {
    val s = SolutionD()
    s.minOperations(intArrayOf(6, 10, 15)).print()
    s.minOperations(intArrayOf(2, 6, 3, 4)).print()
}

class SolutionD {
    fun minOperations(nums: IntArray): Int {
        if (nums.any { it == 1 }) return nums.count { it != 1 }
        var min = Int.MAX_VALUE
        for (i in nums.indices) {
            var g = nums[i]
            for (j in i + 1 until nums.size) {
                g = gcd(g, nums[j])
                if (g == 1) {
                    min = minOf(min, j - i - 1)
                    break
                }
            }
        }
        if (min == Int.MAX_VALUE) return -1
        return min + nums.size
    }
}