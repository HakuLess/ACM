package leetcode.contest.c491

class SolutionA {
    fun trimTrailingVowels(s: String): String {
        val vowels = hashSetOf('a', 'e', 'i', 'o', 'u')
        return s.trimEnd { it in vowels }
    }
}