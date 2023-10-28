package leetcode.contest.b116

class SolutionB {
    fun minChanges(s: String): Int {
        var ans = 0
        for (i in s.indices step 2) {
            if (s[i] != s[i + 1]) ans++
        }
        return ans
    }
}