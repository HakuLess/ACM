package leetcode.contest.c408

import utils.print

fun main() {
    val s = SolutionC()
    s.numberOfSubstrings("111").print()
}

class SolutionC {
    fun numberOfSubstrings(s: String): Int {
        val n = s.length
        if (s.all { it == '1' }) {
            return n * (n + 1) / 2
        }
        if (s.all { it == '0' }) {
            return 0
        }
        if (s.count { it == '0' } == 1) {
            return n * (n + 1) / 2 - 1
        }
        val prefixOnes = IntArray(n + 1)
        val prefixZeros = IntArray(n + 1)

        for (i in 0 until n) {
            prefixOnes[i + 1] = prefixOnes[i] + if (s[i] == '1') 1 else 0
            prefixZeros[i + 1] = prefixZeros[i] + if (s[i] == '0') 1 else 0
        }

        var count = 0

        for (i in 0 until n) {
            var j = i
            while (j < n) {
                val ones = prefixOnes[j + 1] - prefixOnes[i]
                val zeros = prefixZeros[j + 1] - prefixZeros[i]
                if (ones >= zeros * zeros) {
                    count++
                    j++
                } else {
                    j += zeros * zeros - ones
                }
            }
        }

        return count
    }
}