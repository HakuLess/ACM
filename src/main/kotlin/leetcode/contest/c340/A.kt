package leetcode.contest.c340

import kotlin.math.sqrt

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

    fun isPrime(num: Int): Boolean {
        if (num <= 1) return false
        for (i in 2..sqrt(num.toDouble()).toInt()) {
            if (num % i == 0) return false
        }
        return true
    }
}