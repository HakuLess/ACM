package leetcode.contest.b177

import utils.print
import utils.quickPower

fun main() {
    val s = SolutionD()
    s.sumOfNumbers(1, 2, 2).print()
    s.sumOfNumbers(0, 1, 3).print()
    s.sumOfNumbers(5, 5, 10).print()
}

class SolutionD {
    fun sumOfNumbers(l: Int, r: Int, k: Int): Int {
        val mod = 1_000_000_007L
        val m = (r - l + 1).toLong()
        val s = ((l + r).toLong() * m / 2) % mod
        val kLong = k.toLong()
        val mPow = quickPower(m, kLong - 1, mod)
        val pow10k = quickPower(10, kLong, mod)
        val geometric = (pow10k - 1 + mod) % mod
        val inv9 = quickPower(9, mod - 2, mod)
        val result = (((mPow * s) % mod) * geometric % mod) * inv9 % mod
        return result.toInt()
    }
}