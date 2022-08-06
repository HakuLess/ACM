package leetcode.contest.b84

import utils.print

fun main() {
    val s = SolutionD()
    // 73
//    s.minimumReplacement(intArrayOf(19, 7, 2, 24, 11, 16, 1, 11, 23)).print()
    // 6
    s.minimumReplacement(intArrayOf(12, 9, 7, 6, 17, 19, 21)).print()
}

class SolutionD {
    fun minimumReplacement(nums: IntArray): Long {
        var cur = nums.last()
        var ans = 0L
        for (i in nums.indices.reversed()) {
            if (nums[i] <= cur) {
                cur = nums[i]
            } else if (nums[i] % cur == 0) {
                ans += (nums[i] / cur) - 1
            } else {
                ans += (nums[i] / cur)
                cur--
                // 可拆分到的最大值
                while (nums[i] % cur > nums[i] / cur) {
                    cur--
                }
            }
//            println("$i: cur is $cur, ans is $ans")
        }
        return ans
    }
}