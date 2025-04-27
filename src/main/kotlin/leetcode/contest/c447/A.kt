package leetcode.contest.c447

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionA()
    s.countCoveredBuildings(3, "[[1,2],[2,2],[3,2],[2,1],[2,3]]".toGrid()).print()
}

class SolutionA {
    fun countCoveredBuildings(n: Int, buildings: Array<IntArray>): Int {
        val rowMin = IntArray(n + 1) { Int.MAX_VALUE }
        val rowMax = IntArray(n + 1) { Int.MIN_VALUE }
        val colMin = IntArray(n + 1) { Int.MAX_VALUE }
        val colMax = IntArray(n + 1) { Int.MIN_VALUE }

        for ((x, y) in buildings) {
            rowMin[x] = minOf(rowMin[x], y)
            rowMax[x] = maxOf(rowMax[x], y)
            colMin[y] = minOf(colMin[y], x)
            colMax[y] = maxOf(colMax[y], x)
        }

//        rowMin.print()
//        rowMax.print()
//        colMin.print()
//        colMax.print()

        var ans = 0
        for ((x, y) in buildings) {
            if ((y in rowMin[x] + 1..rowMax[x] - 1) && (x in colMin[y] + 1..colMax[y] - 1)) {
                ans++
            }
        }
        return ans
    }
}