package leetcode.contest.b159

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionA()
    s.minSwaps(intArrayOf(131, 100)).print()
}

class SolutionA {
    fun minSwaps(nums: IntArray): Int {
        val oddPos = mutableListOf<Int>()
        val evenPos = mutableListOf<Int>()
        for (i in nums.indices) {
            val v = nums[i]
            if (v % 2 == 0) {
                evenPos.add(i)
            } else {
                oddPos.add(i)
            }
        }

        if (abs(oddPos.size - evenPos.size) > 1) {
            return -1
        }

        var ans = Int.MAX_VALUE

        if (evenPos.size >= oddPos.size) {
            var swaps = 0
            for (i in evenPos.indices) {
                swaps += abs(evenPos[i] - i * 2)
            }
            ans = minOf(ans, swaps)
        }
        if (oddPos.size >= evenPos.size) {
            var swaps = 0
            for (i in oddPos.indices) {
                swaps += abs(oddPos[i] - i * 2)
            }
            ans = minOf(ans, swaps)
        }

        return ans
    }
}