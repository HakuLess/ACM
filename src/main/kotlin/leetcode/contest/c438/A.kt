package leetcode.contest.c438

class SolutionA {
    fun hasSameDigits(s: String): Boolean {
        var digits = s.map { it - '0' }
        while (digits.size > 2) {
            digits = (0 until digits.size - 1).map { (digits[it] + digits[it + 1]) % 10 }
        }
        return digits[0] == digits[1]
    }
}