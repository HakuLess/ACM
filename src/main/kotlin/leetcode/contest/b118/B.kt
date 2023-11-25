package leetcode.contest.b118

import utils.print

fun main() {
    val s = SolutionB()
//    s.maximizeSquareHoleArea(1, 1, intArrayOf(2), intArrayOf(2)).print()

    s.maximizeSquareHoleArea(2, 4, intArrayOf(3, 2), intArrayOf(4, 2)).print()
}

class SolutionB {
    fun maximizeSquareHoleArea(n: Int, m: Int, hBars: IntArray, vBars: IntArray): Int {
        hBars.sort()
        vBars.sort()
        var a = 1
        var b = 1
        var c = 1
        for (i in 1 until hBars.size) {
            if (hBars[i] == hBars[i - 1] + 1) {
                c++
            } else {
                c = 1
            }
            a = maxOf(a, c)
        }

        c = 1
        for (i in 1 until vBars.size) {
            if (vBars[i] == vBars[i - 1] + 1) {
                c++
            } else {
                c = 1
            }
            b = maxOf(b, c)
        }
        val max = minOf(a, b) + 1
        return max * max
    }
}