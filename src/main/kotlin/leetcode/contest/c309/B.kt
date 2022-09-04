package leetcode.contest.c309

import utils.print
import utils.toBigInteger
import java.math.BigInteger
import kotlin.math.abs

fun main() {
    val s = SolutionB()
    s.numberOfWays(1, 2, 3).print()
}

class SolutionB {
    fun numberOfWays(startPos: Int, endPos: Int, k: Int): Int {
        val move = abs(endPos - startPos)
        if (move > k) return 0
        if ((k - move) % 2 != 0) return 0
        val m = (k - move) / 2
        val mod = 1000000007L
        println("$k $m")
        var ans = BigInteger.ONE
        for (i in k downTo (k - m + 1)) {
            ans *= i.toBigInteger()
        }
        for (i in 2..m) {
            ans /= i.toBigInteger()
        }
        return ans.mod(mod.toBigInteger()).toInt()
    }
}