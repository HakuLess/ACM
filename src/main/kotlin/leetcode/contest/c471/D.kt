package leetcode.contest.c471

class SolutionD {
    fun sumOfAncestors(n: Int, edges: Array<IntArray>, nums: IntArray): Long {

        fun squareFree(num: Int): Int {
            var x = num
            var result = 1
            var p = 2
            while (p * p <= x) {
                var cnt = 0
                while (x % p == 0) {
                    x /= p
                    cnt++
                }
                if (cnt % 2 == 1) result *= p
                p++
            }
            if (x > 1) result *= x
            return result
        }

        val pattern = IntArray(n) { squareFree(nums[it]) }

        val adj = Array(n) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            adj[u].add(v)
            adj[v].add(u)
        }

        val freq = HashMap<Int, Int>()
        var total = 0L

        fun dfs(u: Int, parent: Int) {
            val pat = pattern[u]
            total += freq.getOrDefault(pat, 0)
            freq[pat] = freq.getOrDefault(pat, 0) + 1
            for (v in adj[u]) {
                if (v != parent) dfs(v, u)
            }
            freq[pat] = freq[pat]!! - 1
            if (freq[pat] == 0) freq.remove(pat)
        }

        dfs(0, -1)
        return total
    }
}