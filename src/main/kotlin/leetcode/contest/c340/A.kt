package leetcode.contest.c340

import utils.isPrime

class SolutionA {
    fun diagonalPrime(nums: Array<IntArray>): Int {
        val n = nums.size
        val primes = arrayListOf<Int>()
        for (i in 0 until n) {
            if (isPrime(nums[i][i])) primes.add(nums[i][i])
            if (isPrime(nums[i][n - i - 1])) primes.add(nums[i][n - i - 1])
        }
        return if (primes.isEmpty()) 0 else primes.maxOrNull()!!
    }
}