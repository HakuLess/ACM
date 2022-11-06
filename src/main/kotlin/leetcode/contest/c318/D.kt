package leetcode.contest.c318

import utils.print
import utils.toGrid
import kotlin.math.abs

fun main() {
    val s = SolutionD()
    s.minimumTotalDistance(listOf(0, 4, 6), "[[2,2],[6,2]]".toGrid()).print()
    // 6
    s.minimumTotalDistance(listOf(9, 11, 99, 101), "[[10,1],[7,1],[14,1],[100,1],[96,1],[103,1]]".toGrid()).print()
    s.minimumTotalDistance(listOf(9, 11, 99, 101), "[[10,1],[7,1],[14,1],[100,1],[96,1],[103,1]]".toGrid()).print()
}

class SolutionD {
    fun minimumTotalDistance(robot: List<Int>, factory: Array<IntArray>): Long {
        val r = robot.sorted()
        val f = factory.sortedBy { it[0] }

        val seen = HashMap<String, Long>()

        // 当前机器人Index， 工厂Index， 工厂已装载机器人数量
        fun dfs(rIndex: Int, fIndex: Int, c: Int): Long {
            val key = "$rIndex, $fIndex, $c"
            if (key in seen) return seen[key]!!
            if (rIndex == r.size) {
                return 0L
            }
            if (fIndex == f.size) {
                return Long.MAX_VALUE / 2
            }
            if (c == f[fIndex][1]) {
                // 进行下一工厂选择
                return dfs(rIndex, fIndex + 1, 0)
            }
            var ans = abs(r[rIndex] - f[fIndex][0]) + dfs(rIndex + 1, fIndex, c + 1)
            ans = minOf(ans, dfs(rIndex, fIndex + 1, 0))
            return ans.also {
                seen[key] = it
            }
        }
        return dfs(0, 0, 0)
    }

//    fun minimumTotalDistance(robot: List<Int>, factory: Array<IntArray>): Long {
//        val r = robot.sorted()
//        val f = factory.sortedBy { it[0] }
//
//        val n = r.size
//        val m = f.size
//        // dp[i][j] 前i个工厂 负责前j个机器人的最小值
//        val dp = Array<LongArray>(m + 1) { LongArray(n + 1) { Long.MAX_VALUE / 2 } }
//        dp[0][0] = 0L
//        for (i in 0 until m) {
//            for (j in 0 until n + 1) {
//                if (dp[i][j] < Long.MAX_VALUE / 2) {
//                    dp[i + 1][j] = minOf(dp[i + 1][j], dp[i][j])
//                    var cost = 0L
//                    for (k in 1..f[i][1]) {
//                        // 选择k台维修到当前工厂
//                        if (j + k - 1 < n) {
//                            cost += abs(f[i][0] - r[j + k - 1])
//                            dp[i + 1][j + k] = minOf(dp[i + 1][j + k], dp[i][j] + cost)
//                        } else {
//                            break
//                        }
//                    }
//                }
//            }
//        }
////        dp.print()
//        return dp.last().last()
//    }
}