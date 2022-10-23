package leetcode.contest.c316

import utils.gcd
import utils.print

fun main() {
    val s = SolutionB()
    s.subarrayGCD(intArrayOf(9, 3, 1, 2, 6, 3), 3).print()
}

class SolutionB {
    fun subarrayGCD(nums: IntArray, k: Int): Int {
        var ans = 0
        for (i in nums.indices) {
            var gcd = nums[i]
            for (j in i until nums.size) {
                gcd = gcd(gcd, nums[j])
                if (gcd == k) {
                    ans++
                } else if (gcd % k != 0) {
                    break
                }
            }
        }
        return ans
    }
}