package leetcode.contest.c266

import utils.print

fun main() {
    val s = SolutionC()
    s.minimizedMaximum(6, intArrayOf(11, 6)).print()
}

class SolutionC {
    fun minimizedMaximum(n: Int, quantities: IntArray): Int {
        fun check(mid: Int): Boolean {
//            val add = quantities.sumOf { it / mid }
            val add = quantities.map { (it - 1) / mid + 1 }.sum()
            return add <= n
        }

        var left = 0
        var right = quantities.maxOrNull()!!
//        var right = quantities.max()!!
        while (left + 1 < right) {
            val mid = (left + right).ushr(1)
            when {
                check(mid) -> right = mid
                else -> left = mid
            }
        }
        return when {
            check(right) -> right
            else -> left
        }
    }
}