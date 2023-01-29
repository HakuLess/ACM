package leetcode.contest.c330

import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionC()
//    s.putMarbles(intArrayOf(1, 3, 5, 1), 2).print()
//    s.putMarbles(intArrayOf(1, 3), 2).print()
    s.putMarbles(intArrayOf(1, 4, 2, 5, 2), 3).print()
}

class SolutionC {
    fun putMarbles(weights: IntArray, k: Int): Long {
        val l = ArrayList<Long>()
        for (i in 0 until weights.size - 1) {
            val a = weights[i]
            val b = weights[i + 1]
            l.add(b.toLong() + a.toLong())
        }

        var ans = 0L
        l.sortDescending()
        for (i in 0 until k - 1) {
            ans += l[i]
        }
        l.sort()
        for (i in 0 until k - 1) {
            ans -= l[i]
        }
        return ans
    }
}