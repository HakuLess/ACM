package leetcode.contest.c290

import utils.biMin
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.countRectangles("[[1,2],[2,3],[2,5]]".toGrid(), "[[2,1],[1,4]]".toGrid()).print()
}

class SolutionC {
    fun countRectangles(rectangles: Array<IntArray>, points: Array<IntArray>): IntArray {
        rectangles.sortWith(compareBy({ -it[1] }, { it[0] }))
        val map = HashMap<IntArray, Int>()
        for (i in points.indices) {
            map[points[i]] = i
        }
        points.sortWith(compareBy({ -it[1] }, { it[0] }))

        var i = 0
        var j = 0
        val ans = IntArray(points.size)

        while (i in rectangles.indices && j in points.indices) {
            if (rectangles[i][0] == points[j][0]) {
                if (rectangles[i][1] < points[j][1]) {
                    i++
                    ans[j]--
                } else {
                    j++
                }
            } else if (rectangles[i][0] < points[j][0]) {
                i++
            } else if (rectangles[i][0] > points[j][0]) {

            }
        }
        return ans
    }
}