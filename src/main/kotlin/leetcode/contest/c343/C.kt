package leetcode.contest.c343

import utils.print
import utils.toGrid
import kotlin.math.abs

fun main() {
    val s = SolutionC()
    s.minimumCost(intArrayOf(1, 1), intArrayOf(4, 5), "[[1,2,3,3,2],[3,4,4,5,1]]".toGrid()).print()
}

class SolutionC {
    fun minimumCost(start: IntArray, target: IntArray, specialRoads: Array<IntArray>): Int {
        val dp = HashMap<Pair<Int, Int>, Int>()
        dp[Pair(start[0], start[1])] = 0
        dp[Pair(target[0], target[1])] = abs(target[0] - start[0]) + abs(target[1] - start[1])

        specialRoads.sortWith(compareBy({ it[0] }, { it[1] }))

        var change = true
        // 更新状态数组 dp
        while(change) {
            change = false
            val before = dp[Pair(target[0], target[1])]!!
            for (road in specialRoads) {
                val (x1, y1, x2, y2, cost) = road
                dp[Pair(x1, y1)] = minOf(
                    dp.getOrDefault(Pair(x1, y1), abs(x1 - start[0]) + abs(y1 - start[1])),
                    abs(x1 - start[0]) + abs(y1 - start[1])
                )
                dp[Pair(x2, y2)] = minOf(
                    dp.getOrDefault(Pair(x2, y2), abs(x2 - start[0]) + abs(y2 - start[1])),
                    abs(x2 - start[0]) + abs(y2 - start[1])
                )

                for (item in dp) {
                    dp[Pair(x2, y2)] = minOf(
                        dp[Pair(x2, y2)]!!,
                        item.value + cost +
                                abs(item.key.first - x1) + abs(item.key.second - y1)
                    )

                    dp[Pair(target[0], target[1])] = minOf(
                        dp[Pair(target[0], target[1])]!!,
                        dp[Pair(x2, y2)]!! + abs(x2 - target[0]) + abs(y2 - target[1])
                    )
                }
            }
            if (dp[Pair(target[0], target[1])]!! != before) change = true
        }
        return dp[Pair(target[0], target[1])]!!
    }
}