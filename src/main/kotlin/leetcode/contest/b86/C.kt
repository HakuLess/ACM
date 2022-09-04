package leetcode.contest.b86

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.maximumRows("[[0,0,0],[1,0,1],[0,1,1],[0,0,1]]".toGrid(), 2).print()
}

class SolutionC {
    fun maximumRows(mat: Array<IntArray>, cols: Int): Int {
        val n = mat.size
        val m = mat[0].size

        var ans = 0
        fun dfs(i: Int, arr: ArrayList<Int>) {
            if (arr.size == cols) {
                val c = mat.count {
                    it.mapIndexed { index, i ->
                        Pair(index, i)
                    }.filter {
                        it.second == 1
                    }.all {
                        it.first in arr
                    }
                }
                ans = maxOf(ans, c)
                return
            }
            for (j in i until m) {
                arr.add(j)
                dfs(j + 1, arr)
                arr.remove(j)
            }
        }
        dfs(0, arrayListOf())
        return ans
    }
}