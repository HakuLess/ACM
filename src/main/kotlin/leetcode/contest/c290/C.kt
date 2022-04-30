package leetcode.contest.c290

import utils.SortedList
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
//    s.countRectangles("[[1,2],[2,3],[2,5]]".toGrid(), "[[2,1],[1,4]]".toGrid()).print()
    s.countRectangles("[[1,1],[2,2],[3,3]]".toGrid(), "[[1,3],[1,1]]".toGrid()).print()
}

// 双维度大小
class SolutionC {
    fun countRectangles(rectangles: Array<IntArray>, points: Array<IntArray>): IntArray {
        rectangles.sortBy { -it[0] }

        val n = points.size
        val ids = IntRange(0, n - 1).toList().toTypedArray()
        // 坐标x 由大大小排序
        // 对id进行排序
        ids.sortBy { -points[it][0] }

        val ans = IntArray(n)
        val cur = SortedList<Int>()
        var i = 0
        for (id in ids) {
            while (i in rectangles.indices && rectangles[i][0] >= points[id][0]) {
                // 增加纵坐标
                cur.insert(rectangles[i][1])
                i++
            }
            val y = points[id][1]
            ans[id] = cur.largerThanAndEqual(y)
        }
        return ans
    }
}