package leetcode.contest.c389

import utils.print

fun main() {
    val s = SolutionA()
    s.isSubstringPresent("leetcode").print()
}

class SolutionA {
    fun isSubstringPresent(s: String): Boolean {
        for (i in 0 until s.length - 1) {
            val sb = s.substring(i, i + 2)
//            println(sb)
            if (sb in s && sb.reversed() in s) {
                return true
            }
        }
        return false
    }
}