package leetcode.contest.c458

import utils.isPalindrome
import utils.print
import utils.toGraph
import utils.toGrid

fun main() {
    val s = SolutionD()
//    s.maxLen(3, "[[0,1],[1,2]]".toGrid(), "aba").print()
//    s.maxLen(3, "[[0,1],[1,2]]".toGrid(), "abc").print()
//    s.maxLen(4, "[[0,2],[0,3],[3,1]]".toGrid(), "bbac").print()
//
//    // 3
//    s.maxLen(3, "[[1,0],[2,1],[0,2]]".toGrid(), "hjj").print()

    // 2
    s.maxLen(3, "[[2,0],[2,1]]".toGrid(), "mll").print()

    // TODO 处理空状态
//    s.maxLen(1, "[]".toGrid(), "z").print()
}

class SolutionD {

    fun maxLen(n: Int, edges: Array<IntArray>, label: String): Int {

        val graph = Array(n) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            graph[u].add(v)
            graph[v].add(u)
        }

        val totalMasks = 1 shl n
        val dp = Array(n) { Array(n) { IntArray(totalMasks) } }
        val queue = ArrayDeque<Triple<Int, Int, Int>>()
        var ans = 1

        // 初始化单点中心（奇数长度回文）
        for (i in 0 until n) {
            val mask = 1 shl i
            dp[i][i][mask] = 1
            queue.add(Triple(i, i, mask))
        }

        // 初始化边中心（偶数长度回文）
        for ((u, v) in edges) {
            if (label[u] == label[v]) {
                val mask = (1 shl u) or (1 shl v)
                val min = minOf(u, v)
                val max = maxOf(u, v)
                if (dp[min][max][mask] == 0) {
                    dp[min][max][mask] = 2
                    ans = maxOf(ans, 2)
                    queue.add(Triple(min, max, mask))
                }
            }
        }

        // BFS 扩展
        while (queue.isNotEmpty()) {
            val (u, v, mask) = queue.removeFirst()
            for (x in graph[u]) {
                for (y in graph[v]) {
                    if (x == y) continue
                    if ((mask and (1 shl x)) != 0) continue
                    if ((mask and (1 shl y)) != 0) continue
                    if (label[x] != label[y]) continue

                    val newMask = mask or (1 shl x) or (1 shl y)
                    val newU = minOf(x, y)
                    val newV = maxOf(x, y)
                    val newLen = dp[u][v][mask] + 2

                    if (newLen > dp[newU][newV][newMask]) {
                        dp[newU][newV][newMask] = newLen
                        ans = maxOf(ans, newLen)
                        queue.add(Triple(newU, newV, newMask))
                    }
                }
            }
        }

        return ans
    }

//    fun maxLen(n: Int, edges: Array<IntArray>, label: String): Int {
//
//        if (edges.isEmpty()) return 1
//
//        val graph = edges.toGraph(n)
//        var ans = 1
//
//        // 整体去重
//        val seen = HashSet<Triple<Int, Int, String>>()
//
//        fun dfs(node: Int, visited: Int, path: MutableList<Char>) {
//            // 已暴力找到最大值
//            if (ans == n) return
//
//            if (path.isPalindrome()) {
//                ans = maxOf(ans, path.size)
//            }
//
//            for (next in graph.adj[node]) {
//                val key = Triple(visited, next, path.joinToString(""))
//                if ((visited shr next) and 1 == 0 && key !in seen) {
//                    seen.add(key)
//                    path.add(label[next])
//                    dfs(next, visited or (1 shl next), path)
//                    path.removeLast()
//                }
//            }
//        }
//
//        for (i in 0 until n) {
//            dfs(i, 1 shl i, mutableListOf(label[i]))
//        }
//
//        return ans
//    }
}