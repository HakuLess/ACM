package leetcode.contest.c344

import utils.print

fun main() {
    val s = SolutionD()
    s.minIncrements(7, intArrayOf(1, 5, 2, 2, 3, 4, 1)).print()
    s.minIncrements(
        15,
        intArrayOf(764, 1460, 2664, 764, 2725, 4556, 5305, 8829, 5064, 5929, 7660, 6321, 4830, 7055, 3761)
    ).print()
}

class SolutionD {
    fun minIncrements(n: Int, cost: IntArray): Int {
        var left = 0
        var right = 1
        var step = 1
        val tmp = IntArray(n)
        while (left in cost.indices) {
            val sub = cost.toList().subList(left, right)
//            sub.joinToString().print()
            val max = sub.maxOrNull()!!
//            val max = sub.max()!!
            for (i in left until right) {
                tmp[i] = max - cost[i]
            }
            left = right
            step *= 2
            right = left + step
        }
//        tmp.print()
        for (i in tmp.indices.reversed() step 2) {
            if (i == 0) break
            val min = minOf(tmp[i], tmp[i - 1])
            tmp[(i - 1) / 2] += min
            tmp[i] -= min
            tmp[i - 1] -= min
        }
//        tmp.print()
        return tmp.sum() - tmp[0]
    }
}