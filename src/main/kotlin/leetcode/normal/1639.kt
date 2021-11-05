package leetcode.normal

class Solution1639 {
    fun numWays(words: Array<String>, target: String): Int {
        // char & index = cnt
        val n = words[0].length
        val mat = Array<IntArray>(26) { IntArray(n) }
        words.forEach {
            for (i in it.indices) {
                mat[it[i] - 'a'][i]++
            }
        }

        val mod = 1000000007L

        val seen = HashMap<String, Long>()

        fun dfs(cur: Int, start: Int): Long {
            if (cur == target.length) return 1L
            if (start == n) return 0L
            val key = "$cur,$start"
            if (key in seen) return seen[key]!!
            var ans = 0L
            ans += dfs(cur + 1, start + 1) * mat[target[cur] - 'a'][start]
            ans += dfs(cur, start + 1)
            ans %= mod
            return ans.also { seen[key] = it }
        }

        return dfs(0, 0).let {
            (it % mod).toInt()
        }
    }
}