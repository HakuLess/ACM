package leetcode.contest.c381

import utils.Graph
import utils.floyd
import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionD()

//    s.countOfPairs(10, 1, 1).print()
    // 20, 16, 14, 12, 10, 8, 6, 4, 0, 0
    s.countOfPairs(10, 1, 3).print()
    // 20, 18, 14, 12, 10, 8, 6, 2, 0, 0
    s.countOfPairs(10, 1, 4).print()
    // 20, 22, 14, 12, 10, 8, 4, 0, 0, 0
    s.countOfPairs(10, 1, 5).print()
    s.countOfPairs(10, 1, 6).print()
}

class SolutionD {
    // 不会！！
    fun countOfPairs(n: Int, x: Int, y: Int): LongArray {
        val ans = LongArray(n + 1)
        val xIndex = minOf(x, y) - 1
        val yIndex = maxOf(x, y) - 1

        for (i in 0 until n) {
            if (abs(i - xIndex) + 1 > abs(i - yIndex)) {
                ans[1] += 1L
                ans[n - i] -= 1L
            } else {
                val d = abs(i - xIndex) + 1
                val sep = i + d + (yIndex - i - d) / 2
                ans[1] += 1L
                ans[sep - i + 1] -= 1L
                ans[d + 1] += 1L
                ans[d + yIndex - sep] -= 1L
                ans[d] += 1L
                ans[d + n - yIndex] -= 1L
            }
        }

        val cumulativeSum = ans.copyOf()
        for (i in 1 until cumulativeSum.size) {
            cumulativeSum[i] += cumulativeSum[i - 1]
        }

        return cumulativeSum.sliceArray(IntRange(1, n)).map { it * 2 }.toLongArray()
    }
}