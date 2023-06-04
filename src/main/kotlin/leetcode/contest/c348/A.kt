package leetcode.contest.c348

class SolutionA {
    fun minimizedStringLength(s: String): Int {
        return s.toCharArray().toHashSet().size
    }
}