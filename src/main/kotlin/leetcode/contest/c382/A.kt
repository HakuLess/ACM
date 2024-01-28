package leetcode.contest.c382

class SolutionA {
    fun countKeyChanges(s: String): Int {
        var ans = 0
        for (i in s.indices) {
            if (i == 0) continue
            if (s[i].lowercaseChar() != s[i - 1].lowercaseChar()) {
                ans++
            }
        }
        return ans
    }
}