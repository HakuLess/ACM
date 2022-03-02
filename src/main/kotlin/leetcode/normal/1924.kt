package leetcode.normal

import utils.*

fun main() {
    val s = Solution1924()
    s.outerTrees("[[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]".toGrid()).print()
    s.outerTrees("[[1,2],[2,2],[4,2]]".toGrid()).print()
}

// 最小圆覆盖 Welzl 算法
class Solution1924 {
    fun outerTrees(trees: Array<IntArray>): DoubleArray {
        val points = ArrayList<Point>()
        trees.forEach {
            points.add(Point(it[0].toDouble(), it[1].toDouble()))
        }
        points.shuffle()
        // 先以第0个点为圆心，0.0为半径
        var circle = Circle(points[0].x, points[0].y, 0.0)
        for (i in 1 until points.size) {
            if (points[i] !in circle) {
                // 该点不在当前圆内，新加入的点为圆心
                circle = Circle(points[i].x, points[i].y, 0.0)
                // 找到i之前的j、k两点，使3点确定的圆包含当前所有点
                for (j in 0 until i) {
                    if (points[j] !in circle) {
                        val x = (points[i].x + points[j].x) / 2.0
                        val y = (points[i].y + points[j].y) / 2.0
                        circle = Circle(x, y, Point(x, y).distance(points[j]))
                        for (k in 0 until j) {
                            if (points[k] !in circle) {
                                // 三点确定一个圆
                                circle = getCircle(points[i], points[j], points[k])
                            }
                        }
                    }
                }
            }
        }
        return doubleArrayOf(circle.x, circle.y, circle.r)
    }
}