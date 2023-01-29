package leetcode.contest.c330

import utils.print
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
        return l.sortedDescending().take(k - 1).sum() - l.sorted().take(k - 1).sum()
    }
}