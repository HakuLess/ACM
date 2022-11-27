package leetcode.contest.c321

import utils.print

fun main() {
    val s = SolutionB()
    s.appendCharacters("coaching", "coding").print()
}

class SolutionB {
    fun appendCharacters(s: String, t: String): Int {
        var c = 0
        var i = 0
        while (i in t.indices) {
            while (c in s.indices && s[c] != t[i]) {
                c++
            }
            if (c !in s.indices) break
            i++
            c++
        }
        println("$i $c")
        if (i !in t.indices) {
            return 0
        }
        return t.length - i
    }
}