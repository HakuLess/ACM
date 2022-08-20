package leetcode.contest.b85

class SolutionB {
    fun secondsToRemoveOccurrences(s: String): Int {
        var cur = StringBuilder(s)
        fun dfs(s: StringBuilder): StringBuilder {
            var i = 0
            val next = StringBuilder()
            while (i + 1 in s.indices) {
                if (s[i] == '0' && s[i + 1] == '1') {
                    next.append("10")
                    i += 2
                } else {
                    next.append(s[i])
                    i++
                }
            }
            if (i != s.length) {
                next.append(s[i])
            }
            return next
        }
        var ans = 0
        while (cur.contains("01")) {
            cur = dfs(cur)
            ans++
        }
        return ans
    }
}