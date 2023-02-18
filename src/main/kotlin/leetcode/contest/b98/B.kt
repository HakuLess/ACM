package leetcode.contest.b98

import utils.print

fun main() {
    val s = SolutionB()
    s.minimizeSum(intArrayOf(1, 4, 3)).print()
    s.minimizeSum(intArrayOf(1, 4, 7, 8, 5)).print()
    s.minimizeSum(intArrayOf(31, 25, 72, 79, 74, 65)).print()
}

class SolutionB {
    fun minimizeSum(nums: IntArray): Int {
        if (nums.size <= 3) return 0
        nums.sort()
        var ans = Int.MAX_VALUE
        for (i in nums.indices) {
            if (i + nums.size - 3 !in nums.indices) break
//            println("${nums[i + nums.size - 3]} - ${nums[i]}")
            ans = minOf(ans, nums[i + nums.size - 3] - nums[i])
        }
        return ans
    }
}