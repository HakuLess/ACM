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
        val max = c.maxOrNull()!!
//        val max = c.max()!!
        return (c.count { it == max - 1 || it == 0 } == 25 && c.count { it == max } == 1) ||
                (c.count { it == max || it == 0 } == 25 && c.count { it == 1 } == 1) ||
                c.all { it == 1 || it == 0 }
    }
}