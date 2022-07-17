package leetcode.contest.c302

import utils.gcd

class SolutionD {
    fun minOperations(nums: IntArray, numsDivide: IntArray): Int {
        var gcd = numsDivide[0]
        for (i in numsDivide.indices) {
            gcd = gcd(gcd, numsDivide[i])
        }
        nums.sort()
        for (i in nums.indices) {
            if (gcd % nums[i] == 0) {
                return i
            }
        }
        return -1
    }
}