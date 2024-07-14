package leetcode.contest.c406

import utils.print

fun main() {
    val s = SolutionC()
    s.minimumCost(3, 2, intArrayOf(1, 3), intArrayOf(5)).print()
}

class SolutionC {
    fun minimumCost(m: Int, n: Int, horizontalCut: IntArray, verticalCut: IntArray): Int {

        data class Cut(val cost: Int, val type: Char)

        val cuts = mutableListOf<Cut>()
        for (i in horizontalCut.indices) {
            cuts.add(Cut(horizontalCut[i], 'H'))
        }
        for (j in verticalCut.indices) {
            cuts.add(Cut(verticalCut[j], 'V'))
        }

        // 对切割线按开销排序
        cuts.sortBy { -it.cost }

        var totalCost = 0
        var horizontalSegments = 1
        var verticalSegments = 1

        for (cut in cuts) {
            if (cut.type == 'H') {
                // 对于水平切割，开销乘以当前垂直分段的数量
                totalCost += cut.cost * verticalSegments
                horizontalSegments++
            } else {
                // 对于垂直切割，开销乘以当前水平分段的数量
                totalCost += cut.cost * horizontalSegments
                verticalSegments++
            }
        }

        return totalCost
    }
}