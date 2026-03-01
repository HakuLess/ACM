package leetcode.contest.c491

import utils.biMin
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.minimumOR("[[1,5],[2,4]]".toGrid()).print()
    s.minimumOR("[[3,5],[6,4]]".toGrid()).print()
    s.minimumOR("[[7,9,8]]".toGrid()).print()
}

class SolutionC {
    fun minimumOR(grid: Array<IntArray>): Int {

        fun can(grid: Array<IntArray>, target: Int): Boolean {
            for (row in grid) {
                var ok = false
                for (num in row) {
                    if ((num or target) == target) {
                        ok = true
                        break
                    }
                }
                if (!ok) return false
            }

            return true
        }

        var left = 0
        var right = (1 shl 17) - 1
        var ans = right

        while (left <= right) {
            val mid = (left + right) ushr 1

            if (can(grid, mid)) {
                ans = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return ans
    }
}