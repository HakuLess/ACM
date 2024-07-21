package leetcode.contest.c407

class SolutionC {
    fun maxOperations(s: String): Int {
        var ans = 0
        var zeroCount = 0

        for (i in s.indices.reversed()) {
            if (s[i] == '0') {
                if (i + 1 !in s.indices || s[i + 1] == '1') {
                    zeroCount++
                }
            } else {
                ans += zeroCount
            }
        }

        return ans
    }
}