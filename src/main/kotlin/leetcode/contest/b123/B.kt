package leetcode.contest.b123

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionB()
    s.numberOfPairs("[[6,2],[4,4],[2,6]]".toGrid()).print()
    s.numberOfPairs("[[3,1],[1,3],[1,1]]".toGrid()).print()
}

class SolutionB {
    fun numberOfPairs(points: Array<IntArray>): Int {
        val sorted = points.sortedWith(compareBy({ it[0] }, { it[1] }))
        var ans = 0
        for (i in sorted.indices) {
            for (j in points.indices) {
                if (i == j) continue
                // 左上
                val x = points[i]
                // 右下
                val y = points[j]

                var add = true
                if (x[0] > y[0]) add = false
                if (y[1] > x[1]) add = false
                for (k in points.indices) {
                    if (k == i || k == j) continue
                    val z = points[k]
                    if (z[1] in y[1]..x[1] && z[0] in x[0]..y[0]) add = false
                }
                if (add) {
                    ans++
                }
            }
        }
        return ans
    }
}