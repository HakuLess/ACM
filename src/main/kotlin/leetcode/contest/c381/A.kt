package leetcode.contest.c381

import utils.print

fun main() {
    val s = SolutionA()
    // 52
    s.minimumPushes("amrvxnhsewkoipjyuclgtdbfq").print()
}

class SolutionA {
    fun minimumPushes(word: String): Int {
        val a = minOf(word.length, 8)
        val b = maxOf(0, minOf(word.length - 8, 8))
        val c = maxOf(0, minOf(word.length - 16, 8))
        val d = maxOf(0, minOf(word.length - 24, 8))

        return a + 2 * b + 3 * c + 4 * d
    }
}