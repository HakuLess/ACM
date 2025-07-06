package leetcode.contest.b142

import utils.print

fun main() {
    val s = SolutionD()
    s.possibleStringCount("aabbccdd", 7).print()
}

class SolutionD {
    fun possibleStringCount(word: String, k: Int): Int {
        val n = word.length
        val mod = 1_000_000_007L

        // 分段统计每个连续字符的数量
        val vec = mutableListOf<Int>()
        var j = 0
        for (i in 0 until n) {
            if (i == n - 1 || word[i] != word[i + 1]) {
                vec.add(i - j + 1)
                j = i + 1
            }
        }

        val m = vec.size
        var ans = 1L
        for (x in vec) ans = ans * x % mod
        if (m >= k) return ans.toInt()

        // 初始化动态规划数组 f 和 g
        val f = Array(m + 1) { LongArray(k) }
        val g = Array(m + 1) { LongArray(k) }
        f[0][0] = 1
        for (j in 0 until k) g[0][j] = 1

        // 动态规划计算
        // TODO 逻辑都看不懂 容斥
        for (i in 1..m) {
            for (j in 1 until k) {
                var v = 0L
                val t = j - vec[i - 1] - 1
                if (t >= 0) v = g[i - 1][t]
                f[i][j] = (g[i - 1][j - 1] - v + mod) % mod
            }
            for (j in 1 until k) g[i][j] = (g[i][j - 1] + f[i][j]) % mod
        }

        return ((ans - g[m][k - 1] + mod) % mod).toInt()
    }
}