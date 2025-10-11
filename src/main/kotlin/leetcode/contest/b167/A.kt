package leetcode.contest.b167

class SolutionA {
    fun scoreBalance(s: String): Boolean {
        var total = 0
        s.forEach {
            total += (it - 'a' + 1)
        }
        if (total % 2 != 0) return false
        var cur = 0
        for (i in s.indices) {
            cur += (s[i] - 'a' + 1)
            if (cur == total / 2) return true
        }
        return false
    }
}