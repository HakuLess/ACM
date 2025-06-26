package leetcode.contest.c455

import utils.print
import kotlin.math.floor

fun main() {
    val s = SolutionD()
    s.minTime(1, 1, 2, intArrayOf(5), doubleArrayOf(1.0, 1.3)).print()
    s.minTime(3, 2, 3, intArrayOf(2, 5, 8), doubleArrayOf(1.0, 1.5, 0.75)).print()
}

// Not Finished
class SolutionD {
    fun minTime(n: Int, k: Int, m: Int, time: IntArray, mul: DoubleArray): Double {
        val maxState = 1 shl n
        val dp = Array(maxState) { DoubleArray(m) { Double.POSITIVE_INFINITY } }

        dp[maxState - 1][0] = 0.0

        for (campMask in maxState - 1 downTo 0) {
            for (stage in 0 until m) {
                val curTime = dp[campMask][stage]
                if (curTime.isInfinite()) continue

                // 如果所有人已过河，跳过
                if (campMask == 0) continue

                // 枚举过河组合
                fun getGroups(mask: Int): List<Int> {
                    val res = mutableListOf<Int>()
                    var sub = mask
                    while (sub > 0) {
                        if (Integer.bitCount(sub) in 1..k) {
                            res.add(sub)
                        }
                        sub = (sub - 1) and mask
                    }
                    return res
                }

                for (group in getGroups(campMask)) {
                    val groupTime = (0 until n).filter { (group shr it) and 1 == 1 }.maxOf { time[it] }
                    val crossTime = groupTime * mul[stage]
                    val nextStage = (stage + floor(crossTime).toInt() % m) % m
                    val newMask = campMask and group.inv()

                    if (newMask == 0) {
                        // 全部过河
                        dp[0][nextStage] = minOf(dp[0][nextStage], curTime + crossTime)
                    } else {
                        // 必须带船回来
                        for (r in 0 until n) {
                            if ((group shr r) and 1 == 1) {
                                val returnTime = time[r] * mul[nextStage]
                                val backStage = (nextStage + floor(returnTime).toInt() % m) % m
                                dp[campMask or (1 shl r)][backStage] =
                                    minOf(dp[campMask or (1 shl r)][backStage], curTime + crossTime + returnTime)
                            }
                        }
                    }
                }
            }
        }

        val ans = dp[0].minOrNull()!!
        return if (ans == Double.POSITIVE_INFINITY) -1.0 else ans
    }
}