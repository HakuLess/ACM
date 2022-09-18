package leetcode.contest.c311

class SolutionB {
    fun longestContinuousSubstring(s: String): Int {
        var cur = 0
        var ans = 0
        for (i in s.indices) {
            if (i == 0) {
                cur++
            } else {
                if (s[i] == s[i - 1] + 1) {
                    cur++
                } else {
                    cur = 1
                }
            }
            ans = maxOf(ans, cur)
        }
        return ans
    }
}