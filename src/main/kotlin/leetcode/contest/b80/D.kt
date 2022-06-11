package leetcode.contest.b80

import utils.preSumArray
import utils.print

fun main() {
    val s = SolutionD()
    s.countSubarrays(intArrayOf(2, 1, 4, 3, 5), 10L).print()
    s.countSubarrays(intArrayOf(1, 1, 1), 5L).print()
}

class SolutionD {
    fun countSubarrays(nums: IntArray, k: Long): Long {
        val pre = nums.preSumArray(true)
        var l = 0
        var r = 1
        var ans = 0L
        while (l in nums.indices) {
            while (r in pre.indices && (pre[r] - pre[l]) * (r - l) < k) {
//                println("${pre[r]} - ${pre[l]} and $r - $l")
                r++
            }
            ans += r - l - 1
            l++
        }
        return ans
    }
}