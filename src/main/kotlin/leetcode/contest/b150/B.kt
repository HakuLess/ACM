package leetcode.contest.b150

import utils.print
import utils.toGrid
import java.math.BigDecimal

fun main() {
    val s = SolutionB()
    s.separateSquares("[[0,0,2],[1,1,1]]".toGrid()).print()
}

class SolutionB {
    fun separateSquares(squares: Array<IntArray>): Double {

        squares.sortBy { it[1] }

        fun getAreaBelow(y: Double): BigDecimal {
            var area = BigDecimal.ZERO
            for (square in squares) {
                val (x, yi, l) = square
                val top = yi + l
                if (top <= y) {
                    area += (l.toBigDecimal() * l.toBigDecimal())
                } else if (yi < y && top > y) {
                    area += (y - yi).toBigDecimal() * l.toBigDecimal()
                } else {
                    break
                }
            }
            return area
        }

        val lowerBound = 0.0
        val upperBound = squares.maxOf { it[2] }.toDouble()
        var left = lowerBound
        var right = upperBound
        val targetArea = squares.sumOf { it[2].toDouble() * it[2].toDouble() } / 2

        while (right - left > 1e-6) {
            val mid = (left + right) / 2
            val areaBelow = getAreaBelow(mid)
            if (areaBelow < targetArea.toBigDecimal()) {
                left = mid
            } else {
                right = mid
            }
        }

        return (left + right) / 2
    }
}

