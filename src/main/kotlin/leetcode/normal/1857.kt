package leetcode.normal

class Solution1857 {
    fun largestPathValue(colors: String, edges: Array<IntArray>): Int {
        val n = colors.length
        val graph = Array<MutableList<Int>>(n) { mutableListOf() }
        val inDegree = IntArray(n)

        // 构建图
        for ((u, v) in edges) {
            graph[u].add(v)
            inDegree[v]++
        }

        val queue: ArrayDeque<Int> = ArrayDeque()
        for (i in 0 until n) {
            if (inDegree[i] == 0) {
                queue.add(i)
            }
        }

        val dp = Array(n) { IntArray(26) } // 每个节点的每个颜色最大数量
        var visited = 0
        var maxColorCount = 0

        while (queue.isNotEmpty()) {
            val u = queue.removeFirst()
            visited++
            val colorIdx = colors[u] - 'a'
            dp[u][colorIdx]++

            maxColorCount = maxOf(maxColorCount, dp[u][colorIdx])

            for (v in graph[u]) {
                for (c in 0 until 26) {
                    dp[v][c] = maxOf(dp[v][c], dp[u][c])
                }
                inDegree[v]--
                if (inDegree[v] == 0) {
                    queue.add(v)
                }
            }
        }

        // 如果没有访问完所有节点，说明图中有环
        return if (visited < n) -1 else maxColorCount
    }
}