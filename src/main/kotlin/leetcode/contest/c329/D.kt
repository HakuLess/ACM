package leetcode.contest.c329

import utils.SegmentTree
import utils.print
import utils.printInt

fun main() {
    val s = SolutionD()
//    s.minCost(intArrayOf(1, 2, 1, 2, 1, 3, 3), 2).print()
    // 9
    s.minCost(intArrayOf(3, 3, 3, 3, 4, 5, 4, 6, 2, 4, 2, 1, 5, 6, 4, 5, 1, 1, 3, 3), 1).print()
}

class SolutionD {
    fun minCost(nums: IntArray, k: Int): Int {
        val n = nums.size

        val dp = IntArray(n + 1) { Int.MAX_VALUE }
        dp[0] = 0
        for (i in 0 until n) {
            val map = HashMap<Int, Int>()
            var sum = 0
            for (j in i + 1..n) {
                val c = nums[j - 1]
                map[c] = map.getOrDefault(c, 0) + 1
                if (map[c]!! == 2) {
                    sum += 2
                } else if (map[c]!! > 2) {
                    sum++
                }
                dp[j] = minOf(dp[j], dp[i] + sum + k)
            }
        }

        return dp[n]
    }
}