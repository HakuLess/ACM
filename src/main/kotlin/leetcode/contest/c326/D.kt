package leetcode.contest.c326

import utils.getPrime
import utils.print

fun main() {
    val s = SolutionD()
    s.closestPrimes(10, 19).print()
}

class SolutionD {
    fun closestPrimes(left: Int, right: Int): IntArray {
        val primes = getPrime(right + 1)
        val l = ArrayList<Int>()
        for (i in maxOf(2, left)..right) {
            if (primes[i] == 1) {
                l.add(i)
            }
        }
        var ans = intArrayOf(-1, Int.MAX_VALUE / 2)
        for (i in 1 until l.size) {
            if (l[i] - l[i - 1] < ans[1] - ans[0]) {
                ans = intArrayOf(l[i - 1], l[i])
            }
        }
        if (ans[0] == -1) return intArrayOf(-1, -1)
        return ans
    }
}