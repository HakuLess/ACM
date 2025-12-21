package leetcode.contest.c481

import utils.print

fun main() {
    val s = SolutionC()
    // 1
    s.minSwaps(intArrayOf(1, 2, 3), intArrayOf(3, 2, 1)).print()
    // 2
    s.minSwaps(intArrayOf(4, 6, 6, 5), intArrayOf(4, 6, 5, 5)).print()
    // -1
    s.minSwaps(intArrayOf(7, 7), intArrayOf(8, 7)).print()
    // -1
    s.minSwaps(intArrayOf(5, 12, 12), intArrayOf(5, 12, 12)).print()
    // 1
    s.minSwaps(intArrayOf(1, 2), intArrayOf(7, 2)).print()
    // 2
    s.minSwaps(intArrayOf(1, 4, 8), intArrayOf(1, 4, 8)).print()
}

class SolutionC {
    fun minSwaps(nums: IntArray, forbidden: IntArray): Int {

        val n = nums.size

        val numsCount = HashMap<Int, Int>()
        val forbiddenCount = HashMap<Int, Int>()

        val badCounts = HashMap<Int, Int>()
        var totalBad = 0

        for (i in 0 until n) {
            val num = nums[i]
            val forb = forbidden[i]

            numsCount[num] = numsCount.getOrDefault(num, 0) + 1
            forbiddenCount[forb] = forbiddenCount.getOrDefault(forb, 0) + 1

            if (num == forb) {
                totalBad++
                badCounts[num] = badCounts.getOrDefault(num, 0) + 1
            }
        }

        for ((k, v) in numsCount) {
            if (v + forbiddenCount.getOrDefault(k, 0) > n) {
                return -1
            }
        }

        for ((k, v) in forbiddenCount) {
            if (v + numsCount.getOrDefault(k, 0) > n) {
                return -1
            }
        }

        if (totalBad == 0) return 0

        var maxBadFreq = 0
        for (count in badCounts.values) {
            if (count > maxBadFreq) {
                maxBadFreq = count
            }
        }

        return maxOf(maxBadFreq, (totalBad + 1) / 2)
    }
}