package utils

import kotlin.math.abs

/**
 * 几何操作相关工具类
 * 点、线、面
 * */

// 可忽略精度范围
const val EPS = 1e-7

class Point(val x: Double, val y: Double) {

    override fun equals(other: Any?): Boolean {
        if (other !is Point) return false
        return this.x == other.x && this.y == other.y
    }
}

// vertical false
// y = ax + b
// vertical true
// x = c
class Line(val a: Double, val b: Double, val c: Double, val vertical: Boolean = false) {

    operator fun contains(p: Point): Boolean {
        return if (vertical) {
            abs(c - p.x) < EPS
        } else {
            abs(a * p.x + b - p.y) < EPS
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Line) return false
        return when {
            vertical != other.vertical -> {
                false
            }
            vertical -> {
                abs(c - other.c) < EPS
            }
            else -> {
                abs(a - other.a) < EPS && abs(b - other.b) < EPS
            }
        }
    }
}

/**
 * 两点确定一条直线（不同的点）
 * */
fun getLine(a: Point, b: Point): Line {
    if (a == b) throw Exception("Point Same, Cannot get a Line")
    if (a.x == b.x) return Line(0.0, 0.0, a.x, true)
    val o = (b.y - a.y) / (b.x - a.x)
    return Line(o, a.y - a.x * o, 0.0, false)
}

fun IntArray.toPoint(): Point {
    return Point(this[0].toDouble(), this[1].toDouble())
}

val dir4 = arrayOf(
    intArrayOf(0, 1),
    intArrayOf(0, -1),
    intArrayOf(1, 0),
    intArrayOf(-1, 0)
)

val dir8 = arrayOf(
    intArrayOf(0, 1),
    intArrayOf(1, 1),
    intArrayOf(-1, 1),
    intArrayOf(0, -1),
    intArrayOf(1, -1),
    intArrayOf(-1, -1),
    intArrayOf(1, 0),
    intArrayOf(-1, 0)
)