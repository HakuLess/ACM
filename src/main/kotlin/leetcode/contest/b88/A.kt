package leetcode.contest.b88

import utils.print

fun main() {
    val s = SolutionA()
    s.equalFrequency("bac").print()
}

class SolutionA {
    fun equalFrequency(word: String): Boolean {
        val c = IntArray(26)
        word.forEach {
            c[it - 'a']++
        }
        for (i in c.indices) {
            c[i]--
            val max = c.maxOrNull()!!
//            val max = c.max()!!
            if (c.all { it == max || it == 0 }) return true
            c[i]++
        }
        return false
    }
}