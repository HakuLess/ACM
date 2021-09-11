package leetcode.lccup.a

import utils.Circle

class SolutionD {
    fun circleGame(toys: Array<IntArray>, circles: Array<IntArray>, r: Int): Int {
        val cirs = circles.map {
            Circle(it[0].toDouble(), it[1].toDouble(), r.toDouble())
        }
        var ans = 0
        for (i in toys.indices) {
            val it = toys[i]
            if (it[2] > r) continue
            val cur = Circle(it[0].toDouble(), it[1].toDouble(), it[2].toDouble())
            if (cirs.any { cir ->
                    cur in cir
                }) ans++
        }
        return ans
    }
}