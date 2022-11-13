package leetcode.contest.c319

import utils.gcd
import utils.lcm

class SolutionB {
    fun subarrayLCM(nums: IntArray, k: Int): Int {
        var ans = 0
        for (i in nums.indices) {
            var cur = nums[i]
            if (cur == k) ans++
            for (j in i + 1 until nums.size) {
                cur = lcm(cur, nums[j])
                if (cur == k) ans++
            }
        }
        return ans
    }
}