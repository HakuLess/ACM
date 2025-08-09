package leetcode.normal

class Solution808 {
    fun soupServings(n: Int): Double {
        val m = (n + 24) / 25
        if (m >= 179) return 1.0

        val memo = mutableMapOf<Pair<Int, Int>, Double>()

        fun dfs(a: Int, b: Int): Double {
            if (a <= 0 && b <= 0) return 0.5
            if (a <= 0) return 1.0
            if (b <= 0) return 0.0

            val key = Pair(a, b)
            if (memo.containsKey(key)) return memo[key]!!

            return ((dfs(a - 4, b) + dfs(a - 3, b - 1) + dfs(a - 2, b - 2) + dfs(a - 1, b - 3)) / 4.0).also {
                memo[key] = it
            }
        }

        return dfs(m, m)
    }
}