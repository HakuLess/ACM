package leetcode.contest.c452

import utils.Bits
import java.math.BigInteger

class SolutionA {
    fun checkEqualPartitions(nums: IntArray, target: Long): Boolean {
        val n = nums.size
        val bigTarget = BigInteger.valueOf(target)

        var total = BigInteger.ONE
        nums.forEach {
            total *= BigInteger.valueOf(it.toLong())
        }
        total /= bigTarget
        total /= bigTarget
        if (total != BigInteger.ONE) {
            return false
        }

        for (mask in 0 until (1 shl n)) {
            var p = BigInteger.ONE

            for (i in 0 until n) {
                if ((mask shr i) and 1 == 1) {
                    p *= BigInteger.valueOf(nums[i].toLong())
                }
            }

            if (p == bigTarget) {
                return true
            }
        }

        return false
    }
}