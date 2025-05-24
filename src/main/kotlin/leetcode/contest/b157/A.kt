package leetcode.contest.b157

import utils.isPrime
import utils.print
import kotlin.math.sqrt

fun main() {
    val s = SolutionA()
//    s.sumOfLargestPrimes("12234").print()
    s.sumOfLargestPrimes("2").print()
}

class SolutionA {
    fun sumOfLargestPrimes(s: String): Long {

        val l = ArrayList<Long>()
        for (i in s.indices) {
            for (j in i + 1..s.length) {
                val sub = s.substring(i, j).toLong()
//                sub.print()
                if (isPrime(sub)) {
                    l.add(sub)
                }
            }
        }

        return l.sortedDescending().distinct().take(3).sum()
    }
}

fun isPrime(num: Long): Boolean {
    if (num <= 1) return false
    for (i in 2..sqrt(num.toDouble()).toInt()) {
        if (num % i == 0L) return false
    }
    return true
}