package leetcode.contest.c288

import utils.print

fun main() {
    val s = SolutionA()
//    s.largestInteger(1234).print()
//    s.largestInteger(65875).print()
    s.largestInteger(247).print()
}
class SolutionA {
    fun largestInteger(num: Int): Int {
        val a = arrayListOf<Int>()
        val b = arrayListOf<Int>()
        val s = num.toString()
        for (i in s.indices) {
            val v = s[i] - '0'
            if (v % 2 == 0) {
                a.add(v)
            } else {
                b.add(v)
            }
        }
        a.joinToString().print()
        b.joinToString().print()
        a.sort()
        b.sort()
        val ans = arrayListOf<Int>()
        for (i in s.indices) {
            val v = s[i] - '0'
            if (v % 2 == 0) {
                ans.add(a.last())
                a.removeAt(a.lastIndex)
            } else {
                ans.add(b.last())
                b.removeAt(b.lastIndex)
            }
        }
        return ans.joinToString("").toInt()
    }
}