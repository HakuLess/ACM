package leetcode.contest.c403

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.minimumSum("[[1,0,1],[1,1,1]]".toGrid()).print()
}

class SolutionD {
    fun minimumSum(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size

        // 1的点
        val ones = ArrayList<Point>()
        for (x in 0 until n) {
            for (y in 0 until m) {
                if (grid[x][y] == 1) {
                    ones.add(Point(x, y))
                }
            }
        }

        if (ones.size == 3) return 3

        var a = Rect(0, 0, 0, 0)
        var b = Rect(0, 0, 0, 0)
        var c = Rect(0, 0, 0, 0)

        var ans = Int.MAX_VALUE / 2
        fun check(a: Rect, b: Rect, c: Rect) {
            if (ones.all { it in a || it in b || it in c }) {
                // 包含所有1
                if (a and b == null && a and c == null && b and c == null) {
                    // 无重叠部分
                    ans = minOf(ans, a.area + b.area + c.area)
                }
            }
        }

        fun selectRec(minX: Int, minY: Int, func: (Int, Int, Int, Int) -> Unit) {
            for (x0 in minX until n) {
                for (y0 in minY until m) {
                    for (x1 in x0 until n) {
                        for (y1 in y0 until m) {
                            if (ones.any { it in Rect(x0, y0, x1, y1) }) {
                                func(x0, y0, x1, y1)
                            }
                        }
                    }
                }
            }
        }

        selectRec(0, 0) { x0_a, y0_a, x1_a, y1_a ->
            // 选定第一个矩形
            a = Rect(x0_a, y0_a, x1_a, y1_a)

            selectRec(x1_a + 1, 0) { x0_b, y0_b, x1_b, y1_b ->
                // 选定第二个矩形
                b = Rect(x0_b, y0_b, x1_b, y1_b)

                selectRec(x1_b + 1, 0) { x0_c, y0_c, x1_c, y1_c ->
                    // 选定第三个矩形
                    c = Rect(x0_c, y0_c, x1_c, y1_c)
                    check(a, b, c)
                }

                selectRec(0, y1_b + 1) { x0_c, y0_c, x1_c, y1_c ->
                    // 选定第三个矩形
                    c = Rect(x0_c, y0_c, x1_c, y1_c)
                    check(a, b, c)
                }
            }

            selectRec(0, y1_a + 1) { x0_b, y0_b, x1_b, y1_b ->
                // 选定第二个矩形
                b = Rect(x0_b, y0_b, x1_b, y1_b)

                selectRec(x1_b + 1, 0) { x0_c, y0_c, x1_c, y1_c ->
                    // 选定第三个矩形
                    c = Rect(x0_c, y0_c, x1_c, y1_c)
                    check(a, b, c)
                }

                selectRec(0, y1_b + 1) { x0_c, y0_c, x1_c, y1_c ->
                    // 选定第三个矩形
                    c = Rect(x0_c, y0_c, x1_c, y1_c)
                    check(a, b, c)
                }
            }
        }

        return ans
    }

    data class Rect(val x0: Int, val y0: Int, val x1: Int, val y1: Int) {

        val area: Int
            get() = (x1 - x0 + 1) * (y1 - y0 + 1)

        override fun toString(): String {
            return "$x0..$x1 with $y0..$y1"
        }

        operator fun contains(p: Point): Boolean {
            return (p.x in x0..x1) && (p.y in y0..y1)
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

    data class Point(val x: Int, val y: Int)
}