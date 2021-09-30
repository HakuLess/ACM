package leetcode.normal

import utils.Rect
import utils.and
import utils.print

fun main() {
    val s = Solution223()
    s.computeArea(-3, 0, 3, 4, 0, -1, 9, 2).print()
    s.computeArea(-2, -2, 2, 2, 3, 3, 4, 4).print()
}

class Solution223 {
    fun computeArea(ax1: Int, ay1: Int, ax2: Int, ay2: Int, bx1: Int, by1: Int, bx2: Int, by2: Int): Int {
        val rectA = Rect(
            minOf(ax1, ax2).toDouble(),
            maxOf(ax1, ax2).toDouble(),
            minOf(ay1, ay2).toDouble(),
            maxOf(ay1, ay2).toDouble()
        )
        val rectB = Rect(
            minOf(bx1, bx2).toDouble(),
            maxOf(bx1, bx2).toDouble(),
            minOf(by1, by2).toDouble(),
            maxOf(by1, by2).toDouble()
        )
        return (rectA.area + rectB.area - ((rectA and rectB)?.area ?: 0.0).toInt()).toInt()
    }
}