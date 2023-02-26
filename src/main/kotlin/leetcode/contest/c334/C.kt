package leetcode.contest.c334

import java.util.*


fun main() {
    val s = SolutionC()
//    s.maxNumOfMarkedIndices(intArrayOf(3, 5, 2, 4)).print()
//    s.maxNumOfMarkedIndices(intArrayOf(9, 5, 2, 4)).print()
//    s.maxNumOfMarkedIndices(intArrayOf(7, 6, 8)).print()
}

class SolutionC {
    fun maxNumOfMarkedIndices(nums: IntArray): Int {
        nums.sort()
        var ans = 0
        var i = 0
        for (j in (nums.size + 1) / 2 until nums.size) {
            if (nums[i] * 2 <= nums[j]) {
                ans += 2
                i++
            }
        }
        return ans
    }
}