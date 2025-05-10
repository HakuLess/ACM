package leetcode.contest.b156


class SolutionD {
    fun subtreeInversionSum(edges: Array<IntArray>, nums: IntArray, k: Int): Long {

        val n = nums.size

        val adj = Array(n) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            adj[u].add(v)
            adj[v].add(u)
        }

        val children = Array(n) { mutableListOf<Int>() }
        fun build(u: Int, p: Int) {
            for (w in adj[u]) {
                if (w != p) {
                    children[u].add(w)
                    build(w, u)
                }
            }
        }
        build(0, -1)

        val dp = Array(n) { Array(k + 1) { LongArray(2) { Long.MIN_VALUE / 2 } } }

        // 后序 DFS 填 dp
        fun dfs(u: Int) {
            // 先对孩子递归
            for (v in children[u]) dfs(v)

            // 对每个可能的 (x, p) 状态计算 dp[u][x][p]
            for (x in 0..k) {
                for (p in 0..1) {
                    // 两种选择：不翻转 / 翻转（仅当 x>=k）
                    // 1) 不翻转
                    val x0 = minOf(x + 1, k)
                    val p0 = p
                    // 当前节点值乘以当前 p0 的效果
                    val base0 = 1L * nums[u] * if (p0 == 1) -1 else +1
                    var sum0 = base0
                    for (v in children[u]) {
                        sum0 += dp[v][x0][p0]
                    }
                    // 2) 翻转
                    var sum1 = Long.MIN_VALUE / 2
                    if (x >= k) {
                        val x1 = 0
                        val p1 = p xor 1
                        val base1 = 1L * nums[u] * if (p1 == 1) -1 else +1
                        sum1 = base1
                        for (v in children[u]) {
                            sum1 += dp[v][x1][p1]
                        }
                    }
                    dp[u][x][p] = maxOf(sum0, sum1)
                }
            }
        }

        dfs(0)

        return dp[0][k][0]
    }
}