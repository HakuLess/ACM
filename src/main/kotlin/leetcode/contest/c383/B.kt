package leetcode.contest.c383

import utils.print

fun main() {
    val s = SolutionB()
    s.minimumTimeToInitialState("abacaba", 7).print()
//    s.minimumTimeToInitialState("abacaba", 3).print()
}

class SolutionB {
    fun minimumTimeToInitialState(word: String, k: Int): Int {
        var sb = StringBuilder()
        sb.append(word)
        var ans = 1
        if (k > sb.length) return ans
        sb = StringBuilder(sb.removeRange(IntRange(0, k - 1)).toString())
//        println("1 sb is $sb with ${word.startsWith(sb)}")
        while (!word.startsWith(sb)) {
            ans++
            if (k > sb.length) return ans
            sb = StringBuilder(sb.removeRange(IntRange(0, k - 1)).toString())
//            println("2 sb is $sb with ${word.startsWith(sb)}")
        }
        return ans
    }
}