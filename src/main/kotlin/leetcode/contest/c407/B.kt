package leetcode.contest.c407

class SolutionB {
    fun doesAliceWin(s: String): Boolean {
        val vowels = setOf('a', 'e', 'i', 'o', 'u')
        val cnt = s.count { it in vowels }

        if (cnt == 0) return false
        return true
    }
}