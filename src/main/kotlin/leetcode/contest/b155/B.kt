package leetcode.contest.b155

class SolutionB {
    fun baseUnitConversions(conversions: Array<IntArray>): IntArray {
        val mod = 1_000_000_007L

        val n = conversions.size + 1

        val graph = Array<ArrayList<Pair<Int, Long>>>(n) { ArrayList() }
        for ((u, v, f) in conversions) {
            graph[u].add(Pair(v, f.toLong()))
        }

        val ans = LongArray(n)

        fun dfs(u: Int, acc: Long) {
            ans[u] = acc
            for ((v, f) in graph[u]) {
                dfs(v, (acc * f) % mod)
            }
        }

        dfs(0, 1)
        return ans.map { (it % mod).toInt() }.toIntArray()
    }
}