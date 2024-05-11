package leetcode.contest.b130

import utils.biMax
import utils.print
import utils.toGrid
import kotlin.math.abs

fun main() {
    val s = SolutionB()
    s.maxPointsInsideSquare("[[-1,-4],[16,-8],[13,-3],[-12,0]]".toGrid(), "abda").print()
    s.maxPointsInsideSquare("[[-1,-4],[16,-8],[13,-3],[-12,0]]".toGrid(), "abda").print()
}

class SolutionB {
    fun maxPointsInsideSquare(points: Array<IntArray>, s: String): Int {
        var ans = 0
        biMax(0L, Int.MAX_VALUE.toLong()) {
            val set = HashSet<Char>()
            for (i in points.indices) {
                val (x, y) = points[i]
                val c = s[i]
                if (it >= abs(x) && it >= abs(y)) {
                    if (c in set) return@biMax false
                    else set.add(c)
                }
            }
            ans = maxOf(ans, set.size)
            true
        }
        return ans
    }
}