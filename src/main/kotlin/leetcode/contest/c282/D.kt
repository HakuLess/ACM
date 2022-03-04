package leetcode.contest.c282

import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionD()
//    s.minimumFinishTime("[[2,3],[3,4]]".toGrid(), 5, 4).print()
//    s.minimumFinishTime("[[1,10],[2,2],[3,4]]".toGrid(), 6, 5).print()
    s.minimumFinishTime("[[2,2]]".toGrid(), 24, 27).print()
}

class SolutionD {
    fun minimumFinishTime(tires: Array<IntArray>, changeTime: Int, numLaps: Int): Int {
        val max = 20
        val a = LongArray(max + 1) { Int.MAX_VALUE.toLong() }
        for (i in tires.indices) {
            var cur = tires[i][0].toLong()
            var step = tires[i][0].toLong()
            for (j in 0 until max) {
                a[j + 1] = minOf(a[j + 1], 0L + changeTime + cur)
                step = minOf(step * tires[i][1], Int.MAX_VALUE.toLong())
                cur += step
            }
        }
        val dp = LongArray(numLaps + 1) { Long.MAX_VALUE }
        dp[0] = 0
        for (i in 0 until numLaps) {
            for (j in 0 until max) {
                if (i + j in dp.indices) {
                    dp[i + j] = minOf(dp[i + j], dp[i] + a[j])
                }
            }
        }
        return (dp.last() - changeTime).toInt()
    }
}