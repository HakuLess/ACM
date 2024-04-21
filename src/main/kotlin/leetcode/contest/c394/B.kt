package leetcode.contest.c394

import utils.print

fun main() {
    val s = SolutionB()
    // 1
    s.numberOfSpecialChars("abDBAbb").print()
    // 1
    s.numberOfSpecialChars("AbcbDBdD").print()
}

class SolutionB {
    fun numberOfSpecialChars(word: String): Int {
        val lowSet = HashSet<Char>()
        val upSet = HashSet<Char>()
        val removeSet = HashSet<Char>()

        word.forEach {
            if (it.isLowerCase()) {
                lowSet.add(it)
                if (upSet.contains(it.uppercaseChar())) {
                    removeSet.add(it)
                }
            } else {
                if (it.lowercaseChar() !in lowSet) {
                    removeSet.add(it.lowercaseChar())
                }
                if (it !in upSet && it.lowercaseChar() in lowSet) {
                    upSet.add(it)
                }
            }
        }
//        upSet.joinToString().print()
//        removeSet.joinToString().print()
        return upSet.filter { it.lowercaseChar() !in removeSet }.size
    }
}