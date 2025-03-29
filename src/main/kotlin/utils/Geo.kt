package utils

import kotlin.math.abs
import kotlin.math.sqrt

/**
 * 几何操作相关工具类
 * 点、线、面
 * 区间
 * */

// 可忽略精度范围
const val EPS = 1e-7

class Point(val x: Double, val y: Double) {

    override fun equals(other: Any?): Boolean {
        if (other !is Point) return false
        return this.x == other.x && this.y == other.y
    }

    override fun toString(): String {
        return "Point is ($x, $y)"
    }
}

fun Point.distance(p: Point): Double {
    return sqrt((p.x - this.x) * (p.x - this.x) + (p.y - this.y) * (p.y - this.y))
}

// vertical false
// y = ax + b
// vertical true
// x = c
class Line(
    val a: Double,
    val b: Double,
    // 常规用不到c 和 vertical
    val c: Double = 0.0,
    val vertical: Boolean = false,
    var xLeft: Double = Double.NEGATIVE_INFINITY
) {

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

    fun intersect(other: Line): Double {
        return (other.b - b) / (a - other.a)
    }
}

// 凸包优化，单调查询x时可使用
class ConvexHullTrick {
    private val lines = ArrayList<Line>()
    private var pointer = 0

    fun addLine(m: Double, b: Double) {
        val newLine = Line(m, b)
        while (lines.isNotEmpty()) {
            val last = lines.last()
            val x = newLine.intersect(last)
            if (x <= last.xLeft) {
                lines.removeAt(lines.size - 1)
            } else {
                break
            }
        }
        if (lines.isEmpty()) {
            newLine.xLeft = Double.NEGATIVE_INFINITY
        } else {
            newLine.xLeft = newLine.intersect(lines.last())
        }
        lines.add(newLine)
    }

    fun query(x: Long): Double {
        if (pointer >= lines.size) pointer = lines.size - 1
        while (pointer < lines.size - 1 && lines[pointer + 1].xLeft <= x.toDouble()) {
            pointer++
        }
        return lines[pointer].a * x + lines[pointer].b
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

// 六边形方向
val dir6 = arrayOf(
    intArrayOf(0, 1),
    intArrayOf(0, -1),
    intArrayOf(1, 0),
    intArrayOf(1, -1),
    intArrayOf(-1, 0),
    intArrayOf(-1, 1),
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

val dir9 = arrayOf(
    intArrayOf(0, 0),
    intArrayOf(0, 1),
    intArrayOf(1, 1),
    intArrayOf(-1, 1),
    intArrayOf(0, -1),
    intArrayOf(1, -1),
    intArrayOf(-1, -1),
    intArrayOf(1, 0),
    intArrayOf(-1, 0)
)

val dirHouse8 = arrayOf(
    intArrayOf(1, 2),
    intArrayOf(1, -2),
    intArrayOf(-1, 2),
    intArrayOf(-1, -2),
    intArrayOf(2, 1),
    intArrayOf(2, -1),
    intArrayOf(-2, 1),
    intArrayOf(-2, -1)
)

val dir4lp = arrayOf(
    intArrayOf(-1, -1),
    intArrayOf(0, -1),
    intArrayOf(-1, 0),
    intArrayOf(0, 0)
)

/**
 * 圆圈
 * */
class Circle(val x: Double, val y: Double, val r: Double) {

    operator fun contains(c: Circle): Boolean {
        // 圆心的距离平方，是否小于半径的差值平方
        return (x - c.x) * (x - c.x) + (y - c.y) * (y - c.y) <= (r - c.r) * (r - c.r)
    }

    operator fun contains(p: Point): Boolean {
        return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y) <= r * r
    }

    override fun toString(): String {
        return "Point is ($x, $y)  Radius is $r"
    }
}

/**
 * 三点确定一个圆（点均在圆的边上）
 * */
fun getCircle(p0: Point, p1: Point, p2: Point): Circle {
    val a1: Double = p1.x - p0.x
    val b1: Double = p1.y - p0.y
    val c1 = (a1 * a1 + b1 * b1) / 2
    val a2: Double = p2.x - p0.x
    val b2: Double = p2.y - p0.y
    val c2 = (a2 * a2 + b2 * b2) / 2
    val d = a1 * b2 - a2 * b1
    val centerX = p0.x + (c1 * b2 - c2 * b1) / d
    val centerY = p0.y + (a1 * c2 - a2 * c1) / d
    return Circle(centerX, centerY, Point(centerX, centerY).distance(p0))
}

/**
 * 矩形
 *
 * @param x0 横坐标小
 * @param x1 横坐标大
 * @param y0 纵坐标小
 * @param y1 纵坐标大
 * */
class Rect(val x0: Double, val x1: Double, val y0: Double, val y1: Double) {

    val area: Double
        get() = (x1 - x0) * (y1 - y0)

    override fun toString(): String {
        return "$x0..$x1 with $y0..$y1"
    }
}

infix fun Rect.and(other: Rect): Rect? {
    if (other.x1 < this.x0 ||
        other.x0 > this.x1 ||
        other.y1 < this.y0 ||
        other.y0 > this.y1
    ) return null
    return Rect(
        maxOf(this.x0, other.x0),
        minOf(this.x1, other.x1),
        maxOf(this.y0, other.y0),
        minOf(this.y1, other.y1)
    )
}

// 区间
class Interval(val left: Int, val right: Int) : Comparable<Interval> {

    override fun compareTo(other: Interval): Int {
        return this.left - other.left
    }

    override fun toString(): String {
        return "$left..$right"
    }

}

infix fun Interval.and(other: Interval): Interval? {
    // [4, 5] 与 [6, 7] 可合并则用1
    // [4, 5] 与 [5, 7] 可合并则用0
    val offset = 1
    if (other.left > this.right + offset || other.right < this.left - offset) return null
    return Interval(minOf(this.left, other.left), maxOf(this.right, other.right))
}

/**
 *
 * */
class Triangle(val p: Point, val q: Point, val r: Point) {

}

fun Triangle.area(): Double {
    return 0.5 * abs(p.x * q.y + q.x * r.y + r.x * p.y - p.y * q.x - q.y * r.x - r.y * p.x)
}