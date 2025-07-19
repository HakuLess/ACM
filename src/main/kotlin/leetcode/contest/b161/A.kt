package leetcode.contest.b161

import utils.isPrime
import kotlin.math.abs

class SolutionA {
    fun splitArray(nums: IntArray): Long {
        val a = ArrayList<Long>()
        val b = ArrayList<Long>()
        for (i in nums.indices) {
            if (isPrime(i)) {
                a.add(nums[i].toLong())
            } else {
                b.add(nums[i].toLong())
            }
        }
        return abs(a.sum() - b.sum())
    }
}