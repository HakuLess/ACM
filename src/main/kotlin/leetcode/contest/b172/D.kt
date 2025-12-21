package leetcode.contest.b172

import utils.print

fun main() {
    val s = SolutionD()
    // 3
    s.lastInteger(8).print()
    // 1
    s.lastInteger(5).print()
    s.lastInteger(100000000).print()
}

class SolutionD {
    fun lastInteger(n: Long): Long {
        var head = 1L
        var step = 1L
        var remaining = n
        var left = true

        while (remaining > 1) {
            if (left) {
            } else {
                if (remaining % 2L == 0L) {
                    head += step
                }
            }

            remaining = (remaining + 1) / 2
            step *= 2
            left = !left
        }

        return head
    }
}