package leetcode.contest.c478

class SolutionB {
    fun maxDistinct(s: String): Int {
        return s.toHashSet().size
    }
}