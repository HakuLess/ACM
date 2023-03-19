package leetcode.contest.c337

import kotlin.math.abs

class SolutionB {
    fun checkValidGrid(grid: Array<IntArray>): Boolean {
        val l = ArrayList<Triple<Int, Int, Int>>()
        for (x in grid.indices) {
            for (y in grid[0].indices) {
                l.add(Triple(grid[x][y], x, y))
            }
        }
        l.sortBy { it.first }
        if (l[0] != Triple(0, 0, 0)) return false
        var pre = l[0]
        for (i in 1 until l.size) {
            val (v, x, y) = l[i]
            if (v - pre.first != 1) return false
            if (abs(x - pre.second) == 1 && abs(y - pre.third) == 2) {
                pre = l[i]
                continue
            } else if (abs(x - pre.second) == 2 && abs(y - pre.third) == 1) {
                pre = l[i]
                continue
            } else {
                return false
            }
        }
        return true
    }
}