package leetcode.contest.b62

class Solution5873 {
    fun maxConsecutiveAnswers(answerKey: String, k: Int): Int {
        fun dfs(c: Char): Int {
            val l = ArrayList<Int>()
            var left = 0
            var ans = 0
            for (i in answerKey.indices) {
                if (answerKey[i] != c) {
                    l.add(i)
                    if (l.size > k) {
                        left = l.removeAt(0) + 1
                    }
                }
                ans = maxOf(ans, i - left)
            }
            return ans
        }
        return maxOf(dfs('T'), dfs('F')) + 1
    }
}