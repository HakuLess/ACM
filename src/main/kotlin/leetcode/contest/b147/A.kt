package leetcode.contest.b147

import utils.print

fun main() {
    val s = SolutionA()
    s.hasMatch("luck", "u*").print()
    s.hasMatch("leetcode", "ee*e").print()
    s.hasMatch("car", "c*v").print()
}

class SolutionA {
    fun hasMatch(s: String, p: String): Boolean {
        val split = p.split('*')
//        println(split.size)
        if (split[0].isEmpty()) {
            return s.contains(split[1])
        }
        if (split[1].isEmpty()) {
            return s.contains(split[0])
        }
        val a = s.indexOf(split[0])
        if (a == -1) return false
//        s.substring(a + split[0].length).print()
        val b = s.substring(a + split[0].length).indexOf(split[1])
        if (b == -1) return false
        return true
    }
}