package leetcode.contest.c335

import utils.gcd
import utils.print

fun main() {
    val s = SolutionC()
    s.findValidSplit(intArrayOf(4, 7, 8, 15, 3, 5)).print()
    s.findValidSplit(intArrayOf(4, 7, 15, 8, 3, 5)).print()
}

class SolutionC {
    fun findValidSplit(nums: IntArray): Int {
        var ans = -1
        for (i in 0 until nums.lastIndex) {
            ans = maxOf(ans, i)
            for (j in nums.lastIndex downTo i + 1) {
                if (gcd(nums[i], nums[j]) != 1) {
                    ans = maxOf(ans, j)
                    break
                }
            }
            if (ans == nums.lastIndex) return -1
            if (ans == i) return ans
        }
        return -1
    }
}