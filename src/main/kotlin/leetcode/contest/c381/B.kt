package leetcode.contest.c381

import utils.Graph
import utils.floyd
import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionB()

    s.countOfPairs(10, 1, 1).print()
    s.countOfPairs(10, 1, 3).print()
    s.countOfPairs(10, 1, 4).print()
    s.countOfPairs(10, 1, 5).print()
    s.countOfPairs(10, 1, 6).print()
//    s.countOfPairs(10, 2, 4).print()
}

class SolutionB {
    fun countOfPairs(n: Int, x: Int, y: Int): IntArray {
        val ans = IntArray(n)

        val a = minOf(x, y) - 1
        val b = maxOf(x, y) - 1

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (i == j) continue
                var dis = abs(i - j)
                dis = minOf(dis, abs(i - a) + abs(j - b) + 1)
                dis = minOf(dis, abs(i - b) + abs(j - a) + 1)
//                println("$i..$j with $dis")
                ans[dis - 1]++
            }
        }

        return ans
    }
}