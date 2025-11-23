package leetcode.contest.c477

import utils.print

fun main() {
    val s = SolutionA()
    s.sumAndMultiply(0).print()
}

class SolutionA {
    fun sumAndMultiply(n: Int): Long {
        if (n == 0) return 0L
        val arr = n.toString().filter { it != '0' }.map { (it - '0').toLong() }
        return arr.sum() * arr.joinToString("").toLong()
    }
}