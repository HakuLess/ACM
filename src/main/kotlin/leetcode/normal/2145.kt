package leetcode.normal

import utils.print

fun main() {
    val s = Solution2145()
    s.numberOfArrays(intArrayOf(1, -3, 4), 1, 6).print()
    s.numberOfArrays(intArrayOf(-40), -46, 53).print()
    s.numberOfArrays(intArrayOf(100000, 100000, 100000, 100000, 100000, 100000), -100000, 100000).print()
}

class Solution2145 {
    fun numberOfArrays(differences: IntArray, lower: Int, upper: Int): Int {
        var min = Long.MAX_VALUE
        var max = Long.MIN_VALUE

        var cur = 0L
        differences.forEach {
            cur += it
            min = minOf(min, cur)
            max = maxOf(max, cur)
        }

        val a = maxOf(lower.toLong(), lower - min)
        val b = minOf(upper.toLong(), upper - max)

//        println("$a $b")

        return maxOf(0, b - a + 1).toInt()
    }
}