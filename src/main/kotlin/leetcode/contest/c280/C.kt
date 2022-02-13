package leetcode.contest.c280

import utils.print
import utils.toArrayList

fun main() {
    val s = SolutionC()
    s.minimumRemoval(intArrayOf(4, 1, 6, 5)).print()
    s.minimumRemoval(intArrayOf(2, 10, 3, 2)).print()
    s.minimumRemoval(intArrayOf(1, 1, 1, 1)).print()
    s.minimumRemoval(intArrayOf(1, 1, 1, 2)).print()
}

class SolutionC {
    fun minimumRemoval(beans: IntArray): Long {
        var sum = 0L
        beans.forEach {
            sum += it
        }
        beans.sort()
        var ans = sum
        for (i in beans.indices) {
            ans = minOf(ans, sum - (beans.size - i).toLong() * beans[i])
        }
        return ans
    }
}