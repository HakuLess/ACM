package leetcode.contest.c326

import utils.getPrime
import utils.print

fun main() {
    val s = SolutionB()
    s.distinctPrimeFactors(intArrayOf(2, 4, 3, 7, 10, 6)).print()
    s.distinctPrimeFactors(intArrayOf(2, 4, 8, 16)).print()
    s.distinctPrimeFactors(intArrayOf(27)).print()
}

class SolutionB {
    fun distinctPrimeFactors(nums: IntArray): Int {
        val primes = getPrime(1100)
        val ans = HashSet<Int>()
        nums.forEach {
            var cur = it
            ans.add(primes[cur])
            while (primes[cur] != 1) {
                ans.add(primes[cur])
                cur /= primes[cur]
            }
            ans.add(cur)
        }
        ans.remove(1)
        return ans.size
    }
}