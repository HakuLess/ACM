package leetcode.contest.b112

class SolutionA {
    fun canBeEqual(s1: String, s2: String): Boolean {
        val (a, b, c, d) = s1.toCharArray()
        return s1 == s2
                || "${c}${b}${a}${d}" == s2
                || "${a}${d}${c}${b}" == s2
                || "${c}${d}${a}${b}" == s2
    }
}