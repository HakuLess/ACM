package leetcode.contest.c442

class SolutionB {
    fun numberOfComponents(properties: Array<IntArray>, k: Int): Int {
        val n = properties.size
        val sets = properties.map { it.toSet() }

        val graph = Array(n) { mutableListOf<Int>() }
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                if (sets[i].intersect(sets[j]).size >= k) {
                    graph[i].add(j)
                    graph[j].add(i)
                }
            }
        }

        val seen = BooleanArray(n)
        var ans = 0

        fun dfs(node: Int) {
            seen[node] = true
            for (neighbor in graph[node]) {
                if (!seen[neighbor]) {
                    dfs(neighbor)
                }
            }
        }

        for (i in 0 until n) {
            if (!seen[i]) {
                ans++
                dfs(i)
            }
        }
        return ans
    }
}