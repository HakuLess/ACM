package leetcode.contest.c375

import utils.print

fun main() {
    val s = SolutionC()
    s.countSubarrays(intArrayOf(1, 3, 2, 3, 3), 2).print()
}

class SolutionC {
    fun countSubarrays(nums: IntArray, k: Int): Long {
        var ans = 0L
        val max = nums.maxOrNull()!!
        val n = nums.size
        var preLeft = -1
        var left = 0
        var c = 0
        for (right in nums.indices) {
            if (nums[right] == max) {
                c++
            }
            while (left in nums.indices && nums[left] != max) {
                left++
            }
            while (left in nums.indices && c > k) {
                if (nums[left] == max) {
                    c--
                }
                left++
            }
            if (c == k) {
//                println("$left - $preLeft to $right with ${(left - preLeft) * (n - right)}")
                ans += (left - preLeft).toLong() * (n - right)

                preLeft = left
                left++
                c--
            }
        }
        return ans
    }
}