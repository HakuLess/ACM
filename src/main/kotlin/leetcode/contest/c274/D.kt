package leetcode.contest.c274

import utils.Graph
import utils.getBranches
import utils.print


fun main() {
    val s = SolutionD()
//    s.maximumInvitations(intArrayOf(1, 0, 0, 2, 1, 4, 7, 8, 9, 6, 7, 10, 8)).print()
//    s.maximumInvitations(intArrayOf(1, 2, 3, 4, 5, 6, 3, 8, 9, 10, 11, 8)).print()
    s.maximumInvitations(intArrayOf(1, 0, 3, 2, 5, 6, 7, 4, 9, 8, 11, 10, 11, 12, 10)).print()
//    s.maximumInvitations(intArrayOf(2, 2, 1, 2)).print()
//    s.maximumInvitations(intArrayOf(1, 2, 0)).print()
//    s.maximumInvitations(intArrayOf(3, 0, 1, 4, 1)).print()
}

// 基环内向树
// 最大环 or 最长链
class SolutionD {
    fun maximumInvitations(favorite: IntArray): Int {
        val n = favorite.size
        val g = Graph(n)
        for (i in favorite.indices) {
            // 构造基环内向树
            g.addEdgeOri(favorite[i], i)
        }

        val (branch, isVisited) = g.getBranches()
//        branch.print()
        var ans = 0
        var tmp = 0
        // 计算每一个点作为起点时 环的长度 or 链的长度
        for (i in 0 until n) {
            if (isVisited[i]) continue
            var len = 0
            var u = i
            while (true) {
                val v = favorite[u]
                isVisited[u] = true
                len++
                if (isVisited[v]) break
                u = v
            }
            ans = if (len == 2) {
                // 二元环可以将两侧分支都带上
                tmp += 2 + branch[i] + branch[favorite[i]]
                maxOf(ans, tmp)
            } else {
                maxOf(ans, len)
            }
        }
        return ans
    }
}