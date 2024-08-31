package leetcode.contest.b138

class SolutionB {
    fun stringHash(s: String, k: Int): String {
        val result = StringBuilder()
        for (i in s.indices step k) {
            val substring = s.substring(i, i + k)
            var sum = 0
            for (char in substring) {
                sum += char - 'a'
            }
            val hashedChar = 'a' + (sum % 26)
            result.append(hashedChar)
        }
        return result.toString()
    }
}