package leetcode.contest.c303

class SolutionA {
    fun repeatedCharacter(s: String): Char {
        val seen = HashSet<Char>()
        for (i in s.indices) {
            val c = s[i]
            if (c in seen) return c
            seen.add(c)
        }
        return ' '
    }
}