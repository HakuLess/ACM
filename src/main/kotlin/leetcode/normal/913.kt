package leetcode.normal

import utils.print
import utils.toGrid

fun main() {
    val s = Solution913()
    s.catMouseGame("[[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]".toGrid()).print()
    s.catMouseGame("[[1,3],[0],[3],[0,2]]".toGrid()).print()
//    // 1
    s.catMouseGame("[[2,3],[3,4],[0,4],[0,1],[1,2]]".toGrid()).print()
////     2
    s.catMouseGame("[[2,3],[2],[0,1],[0,4],[3]]".toGrid()).print()
//    // 2
    s.catMouseGame("[[2,6],[2,4,5,6],[0,1,3,5,6],[2],[1,5,6],[1,2,4],[0,1,2,4]]".toGrid()).print()
//    // 1
    s.catMouseGame("[[6],[4],[9],[5],[1,5],[3,4,6],[0,5,10],[8,9,10],[7],[2,7],[6,7]]".toGrid()).print()
}

class Solution913 {
    fun catMouseGame(graph: Array<IntArray>): Int {
        val n = graph.size
        val memo = HashMap<Int, Int>()
        fun dfs(mouse: Int, cat: Int, step: Int): Int {
            val isMouse = step % 2 == 0
            val key = (mouse * 10000 + cat * 100 + step)
            if (key in memo) return memo[key]!!
            if (step >= n * 2) return 0
            if (mouse == 0) return 1
            if (mouse == cat) return 2

            var ans = if (isMouse) 2 else 1
            val target = if (isMouse) 1 else 2
            graph[if (isMouse) mouse else cat].forEach {
                if (!isMouse && it == 0) {

                } else {
                    val cur = if (isMouse) dfs(it, cat, step + 1) else dfs(mouse, it, step + 1)
                    if (cur == target) {
                        return target.also {
                            memo[key] = it
                        }
                    } else if (cur == 0) {
                        ans = 0
                    }
                }
            }
            return ans.also {
                memo[key] = it
            }
        }
        return dfs(1, 2, 0)
    }
}