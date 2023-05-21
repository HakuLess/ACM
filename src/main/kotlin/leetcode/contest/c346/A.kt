package leetcode.contest.c346

import utils.print

fun main() {
    val s = SolutionA()
    s.minLength("ABFCACDB").print()
}

class SolutionA {
    fun minLength(s: String): Int {
        var sb = StringBuilder(s)
        val set = hashSetOf("AB", "CD")
        while (set.any { it in sb }) {
            set.forEach {
                val index = sb.indexOf(it)
                if (index != -1) {
                    sb = StringBuilder(sb.removeRange(index, index + 2))
                }
            }
        }
        return sb.length
    }
}