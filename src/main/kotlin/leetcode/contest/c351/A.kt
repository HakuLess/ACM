package leetcode.contest.c351

import utils.gcd

class SolutionA {
    fun countBeautifulPairs(nums: IntArray): Int {
        var ans = 0
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (gcd(nums[i].toString()[0] - '0', nums[j].toString().last() - '0') == 1) {
                    ans++
                }
            }
        }
        return ans
    }
}