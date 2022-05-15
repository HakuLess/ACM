package leetcode.normal

import utils.Point
import utils.Triangle
import utils.area

class Solution812 {
    fun largestTriangleArea(points: Array<IntArray>): Double {
        var ans = 0.0
        for (i in 0 until points.size - 2) {
            for (j in i + 1 until points.size - 1) {
                for (k in i + 2 until points.size) {
                    val triangle = Triangle(
                        Point(points[i][0].toDouble(), points[i][1].toDouble()),
                        Point(points[j][0].toDouble(), points[j][1].toDouble()),
                        Point(points[k][0].toDouble(), points[k][1].toDouble()),
                    )
                    ans = maxOf(ans, triangle.area())
                }
            }
        }
        return ans
    }
}