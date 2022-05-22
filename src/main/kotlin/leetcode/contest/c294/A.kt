package leetcode.contest.c294

class SolutionA {
    fun percentageLetter(s: String, letter: Char): Int {
        return ((s.count { it == letter }.toDouble() / s.length.toDouble()) * 100).toInt()
    }
}