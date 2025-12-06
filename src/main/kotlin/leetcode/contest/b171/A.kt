package leetcode.contest.b171

import utils.isPrime

class SolutionA {
    fun completePrime(num: Int): Boolean {
        if (!isPrime(num)) return false

        val s = num.toString()
        val n = s.length

        for (i in 1..n) {
            val prefix = s.take(i).toInt()
            if (!isPrime(prefix)) return false
        }

        for (i in 0 until n) {
            val suffix = s.takeLast(i).toInt()
            if (!isPrime(suffix)) return false
        }

        return true
    }
}