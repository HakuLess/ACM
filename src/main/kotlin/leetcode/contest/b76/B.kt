package leetcode.contest.b76

import utils.print

fun main() {
    val s = SolutionB()
    s.waysToBuyPensPencils(20, 10, 5).print()
}

class SolutionB {
    fun waysToBuyPensPencils(total: Int, cost1: Int, cost2: Int): Long {
        var ans = 0L
        var i = 0
        while (i * cost1 <= total) {
            val left = total - i * cost1
            ans += left / cost2 + 1
            i++
        }
        return ans
    }
}