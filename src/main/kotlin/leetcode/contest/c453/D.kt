package leetcode.contest.c453

// TODO Wrong Not Finished
class SolutionD {
    fun minOperations(word1: String, word2: String): Int {
        val n = word1.length
        val dp = IntArray(n + 1) { Int.MAX_VALUE }
        dp[0] = 0

        fun canTransformOnce(s1: String, s2: String): Boolean {
            if (s1 == s2) return true
            if (s1.length != s2.length) return false

            // 替换操作：统计不同字符数
            val diff = s1.indices.count { s1[it] != s2[it] }
            if (diff == 1) return true

            // 交换操作：最多两个字符不等，且互相可以交换
            if (diff == 2) {
                val i = s1.indices.filter { s1[it] != s2[it] }
                return s1[i[0]] == s2[i[1]] && s1[i[1]] == s2[i[0]]
            }

            // 反转操作：直接比较 s1.reverse() == s2
            if (s1.reversed() == s2) return true

            return false
        }

        for (i in 1..n) {
            for (j in 0 until i) {
                val s1 = word1.substring(j, i)
                val s2 = word2.substring(j, i)
                if (canTransformOnce(s1, s2)) {
                    dp[i] = minOf(dp[i], dp[j] + 1)
                }
            }
        }

        return dp[n]
    }
}