package leetcode.contest.c391

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    // 12
    s.minimumDistance("[[3,10],[5,15],[10,2],[4,4]]".toGrid()).print()
    // 10
    s.minimumDistance("[[3,2],[3,9],[7,10],[4,4],[8,10],[2,7]]".toGrid()).print()
}

// 曼哈顿距离最小值
class SolutionD {
    fun minimumDistance(points: Array<IntArray>): Int {
        var ans = Int.MAX_VALUE

        fun calc(ban: Int): List<Int> {
            val a = mutableListOf<Pair<Int, Int>>()
            val b = mutableListOf<Pair<Int, Int>>()
            for (i in points.indices) {
                if (i != ban) {
                    val p = points[i]
                    a.add(Pair(p[0] - p[1], i))
                    b.add(Pair(p[0] + p[1], i))
                }
            }
            a.sortBy { it.first }
            b.sortBy { it.first }
            val d = maxOf(a.last().first - a.first().first, b.last().first - b.first().first)
            ans = minOf(ans, d)
            return listOf(a.last().second, a.first().second, b.last().second, b.first().second)
        }

        for (ban in calc(-1)) {
            calc(ban)
        }
        return ans
    }
}