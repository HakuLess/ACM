package leetcode.contest.c339

class SolutionA {
    fun findTheLongestBalancedSubstring(s: String): Int {
        var ans = 0
        var zero = 0
        var one = 0
        for (i in s.indices) {
            val it = s[i]
            if (it == '0') {
                if (i - 1 in s.indices && s[i - 1] == '1') zero = 0
                zero++
                one = 0
            } else {
                one++
            }
            ans = maxOf(ans, minOf(one, zero) * 2)
        }
        return ans
    }
}