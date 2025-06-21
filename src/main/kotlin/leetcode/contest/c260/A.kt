package leetcode.contest.c260

import utils.print

fun main() {
    val s = Solution5881()
    s.maximumDifference(intArrayOf(1, 5, 2, 10)).print()
}

class Solution5881 {
    fun maximumDifference(nums: IntArray): Int {
        var ans = -1
        var min = Int.MAX_VALUE / 2
        for (i in nums.indices) {
//            println("${nums[i]} - $min")
            ans = maxOf(ans, nums[i] - min)
            min = minOf(min, nums[i])
        }
        return if (ans > 0) ans else -1
    }
}