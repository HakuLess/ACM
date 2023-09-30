package leetcode.contest.c364

class SolutionA {
    fun maximumOddBinaryNumber(s: String): String {
        val a = s.count { it == '1' }
        val b = s.length - a
        val ans = StringBuilder()
        repeat(a - 1) {
            ans.append('1')
        }
        repeat(b) {
            ans.append('0')
        }
        if (a >= 1) {
            ans.append('1')
        }
        return ans.toString()
    }
}