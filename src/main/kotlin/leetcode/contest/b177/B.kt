package leetcode.contest.b177

class SolutionB {
    fun mergeCharacters(s: String, k: Int): String {
        var ans = s
        while (true) {
            var merged = false
            for (i in ans.indices) {
                for (j in i + 1 until ans.length) {
                    if (ans[i] == ans[j] && j - i <= k) {
                        ans = ans.removeRange(j, j + 1)
                        merged = true
                        break
                    }
                }
                if (merged) break
            }
            if (!merged) break
        }
        return ans
    }
}