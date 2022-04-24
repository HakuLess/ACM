package leetcode.lccup.round2021.spring.team

import utils.print
import utils.toGrid
import kotlin.math.abs

fun main() {
    val s = SolutionC()
    s.getMaximumNumber("[[1,1,0],[2,0,1],[4,2,2]]".toGrid()).print()
    s.getMaximumNumber("[[2,0,2],[5,2,0],[4,1,0],[1,2,1],[3,0,2]]".toGrid()).print()
    s.getMaximumNumber("[[0,1,0],[0,0,1]]".toGrid()).print()
}

class SolutionC {
    fun getMaximumNumber(moles: Array<IntArray>): Int {
        moles.sortBy { it[0] }
        val dp = IntArray(moles.size)
        dp.print()
        for (i in moles.indices) {
            var cur = 0
            if (moles[i][0] >= abs(moles[i][1] - 1) + abs(moles[i][2] - 1)) {
                cur = 1
            }
            // 相同时间，不同位置
            for (j in i - 100 until i) {
                if (j in moles.indices) {
                    if (moles[i][0] - moles[j][0] >=
                        abs(moles[i][1] - moles[j][1]) + abs(moles[i][2] - moles[j][2])
                    ) {
                        cur = maxOf(cur, dp[j] + 1)
                    }
                }
            }
            dp[i] = cur
        }
        dp.print()
        return dp.maxOrNull()!!
//        return dp.max()!!
    }
}