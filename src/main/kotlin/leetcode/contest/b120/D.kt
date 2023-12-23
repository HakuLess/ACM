package leetcode.contest.b120

import utils.NTreeNode
import utils.print
import utils.toGrid
import utils.toNTree


fun main() {
    val s = SolutionD()
    s.placedCoins("[[0,1],[0,2],[0,3],[0,4],[0,5]]".toGrid(), intArrayOf(1, 2, 3, 4, 5, 6)).print()
    s.placedCoins("[[0,1],[0,2],[1,3],[1,4],[1,5],[2,6],[2,7],[2,8]]".toGrid(), intArrayOf(1, 4, 2, 3, 5, 7, 8, -4, 2))
        .print()
    s.placedCoins("[[0,1],[0,2]]".toGrid(), intArrayOf(1, 2, -2)).print()
    // [306432,202608,1,1,1,1,1,306432,163212,213864]
    s.placedCoins(
        "[[7,0],[4,3],[4,8],[1,5],[6,2],[2,7],[7,9],[1,8],[1,9]]".toGrid(),
        intArrayOf(37, -48, 30, -67, -84, 36, -96, 24, 29, 38)
    ).print()
}

class SolutionD {
    fun placedCoins(edges: Array<IntArray>, cost: IntArray): LongArray {
        val n = cost.size
        val ans = LongArray(n)
        val root = edges.toNTree()

        fun dfs(cur: NTreeNode): List<Long> {
            val index = cur.`val`
            val l = ArrayList<Long>()
            l.add(cost[index].toLong())
            cur.children.forEach {
                l.addAll(dfs(it))
            }
            if (l.size >= 3) {
                l.sortDescending()
                ans[index] = maxOf(ans[index], l[0] * l[1] * l[2])
                ans[index] = maxOf(ans[index], l[0] * l[l.lastIndex - 1] * l[l.lastIndex])
            } else {
                ans[index] = 1L
            }
            return l.toList()
        }

        dfs(root)
        return ans
    }
}