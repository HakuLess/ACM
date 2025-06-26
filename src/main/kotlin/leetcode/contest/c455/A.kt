package leetcode.contest.c455

import utils.countPrime
import utils.isPrime

class SolutionA {
    fun checkPrimeFrequency(nums: IntArray): Boolean {
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            val c = nums[i]
            map[c] = map.getOrDefault(c, 0) + 1
        }
        return map.values.any {
            isPrime(it)
        }
    }
}