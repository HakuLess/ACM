package leetcode.contest.c479

import utils.getPrimes
import utils.isPrime
import utils.print

fun main() {
    val s = SolutionB()
    s.largestPrime(20).print()
    s.largestPrime(2).print()
    // 5
    s.largestPrime(15).print()
}

class SolutionB {
    fun largestPrime(n: Int): Int {
        val primes = getPrimes(n + 1)
//        primes.joinToString().print()
        var ans = 0
        var sum = 0
        for (item in primes) {
            if (sum + item <= n) {
                sum += item
                if (isPrime(sum)) {
                    ans = sum
                }
            } else {
                break
            }
        }
        return ans
    }
}