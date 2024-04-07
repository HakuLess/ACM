package leetcode.contest.c392

import utils.print

fun main() {
    val s = SolutionC()
    s.minOperationsToMakeMedianK(intArrayOf(2, 5, 6, 8, 5), 4).print()
}

class SolutionC {
    fun minOperationsToMakeMedianK(nums: IntArray, k: Int): Long {
        nums.sort()
//        nums.print()
        val c = nums.size / 2
        var ans = 0L
        if (nums[c] == k) return 0L
        if (nums[c] < k) {
            for (i in c until nums.size) {
                ans += maxOf(0, k - nums[i])
            }
        }
        if (nums[c] > k) {
            for (i in c downTo 0) {
                ans += maxOf(0, nums[i] - k)
            }
        }

        return ans
    }
}