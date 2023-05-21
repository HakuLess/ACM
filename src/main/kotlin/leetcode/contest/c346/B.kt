package leetcode.contest.c346

class SolutionB {
    fun makeSmallestPalindrome(s: String): String {
        val n = s.length - 1
        val sb = StringBuilder(s)
        for (i in 0 until s.length / 2) {
            val min = minOf(sb[i], sb[n - i])
            sb[i] = min
            sb[n - i] = min
        }
        return sb.toString()
    }
}