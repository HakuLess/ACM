package leetcode.contest.b126

import kotlin.math.sign

class SolutionA {
    fun sumOfEncryptedInt(nums: IntArray): Int {
        var ans = 0
        for (i in nums.indices) {
            val a = nums[i].toString().maxOrNull()!! - '0'
            val len = nums[i].toString().length
            var r = 1
            repeat(len) {
                ans += a * r
                r *= 10
            }
        }
        return ans
    }
}