package leetcode.contest.b111

import utils.print

fun main() {
    val s = SolutionC()
    s.minimumOperations(listOf(1, 2)).print()
    s.minimumOperations(listOf(1, 2, 3)).print()
    s.minimumOperations(listOf(1, 1, 2)).print()
    s.minimumOperations(listOf(1, 1, 3)).print()
}

class SolutionC {
    fun minimumOperations(nums: List<Int>): Int {
        var dp1 = 0
        var dp2 = 0
        var dp3 = 0
        nums.forEach {
            dp1 += if (it > 1) 1 else 0
            dp2 = minOf(dp1, dp2 + if (it < 2) 1 else 0) + (if (it > 2) 1 else 0)
            dp3 = minOf(dp1, dp2 + if (it < 2) 1 else 0, dp3 + if (it < 3) 1 else 0)
        }
        return dp3
    }
}