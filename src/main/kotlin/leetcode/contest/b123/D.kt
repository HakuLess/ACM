package leetcode.contest.b123

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.numberOfPairs("[[6,2],[4,4],[2,6]]".toGrid()).print()
    s.numberOfPairs("[[3,1],[1,3],[1,1]]".toGrid()).print()
}

class SolutionD {
    fun numberOfPairs(points: Array<IntArray>): Int {
        val sortedX = points.sortedWith(compareBy({ it[0] }, { -it[1] }))
        var ans = 0

        for (i in sortedX.indices) {
            for (j in i + 1 until sortedX.size) {
                // 左上
                val x = sortedX[i]
                // 右下
                val y = sortedX[j]

                if (x[0] > y[0] || y[1] > x[1]) continue

                var add = true
                for (k in i + 1 until j) {
                    val z = sortedX[k]
                    if (z[1] in y[1]..x[1] && z[0] in x[0]..y[0]) {
                        add = false
                        break
                    }
                }
                if (add) {
                    ans++
                }
            }
        }
        return ans
    }
}