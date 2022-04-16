package leetcode.lccup.round2021.spring.solo

import utils.print

fun main() {
    val s = SolutionD()
    s.defendSpaceCity(intArrayOf(1, 2, 1), intArrayOf(6, 3, 3)).print()
    s.defendSpaceCity(intArrayOf(1, 1, 1, 2, 2, 3, 5), intArrayOf(1, 2, 3, 1, 2, 1, 3)).print()
}

class SolutionD {
    fun defendSpaceCity(time: IntArray, position: IntArray): Int {
        val size = time.maxOrNull()!!
        val grid = Array<ArrayList<Int>>(size) { arrayListOf() }
        for (i in position.indices) {
            grid[time[i] - 1].add(position[i])
        }
        for (i in grid.indices) {
            grid[i].sort()
            grid[i].joinToString().print()
        }
        var ans = 0
        for (i in grid.indices) {
            if (i != 0) {
                ans -= grid[i].count { it in grid[i - 1] }
            }
            for (j in grid[i].indices) {
                ans += 2
            }
        }
        return ans
    }
}