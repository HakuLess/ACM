package leetcode.contest.b79

class SolutionA {
    fun digitCount(num: String): Boolean {
        for (i in num.indices) {
            if (num[i] - '0' != num.count { it == '0' + i }) {
                return false
            }
        }
        return true
    }
}