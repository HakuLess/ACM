package leetcode.contest.c370

import utils.NTreeNode
import utils.print
import utils.toGrid
import utils.toNTree

fun main() {
    val s = SolutionC()
    // 11
    s.maximumScoreAfterOperations("[[0,1],[0,2],[0,3],[2,4],[4,5]]".toGrid(), intArrayOf(5, 2, 5, 2, 1, 1)).print()
}

class SolutionC {
    fun maximumScoreAfterOperations(edges: Array<IntArray>, values: IntArray): Long {
        val root = edges.toNTree()
        fun dfs(cur: NTreeNode): Long {
            if (cur.children.isEmpty()) {
                return values[cur.`val`].toLong()
            }
            var tmp = 0L
            cur.children.forEach {
                tmp += dfs(it)
            }
            if (tmp > values[cur.`val`]) {
                return values[cur.`val`].toLong()
            } else {
                return tmp
            }
        }
        var ans = 0L
        values.forEach {
            ans += it
        }
        ans -= dfs(root)
        return ans
    }
}