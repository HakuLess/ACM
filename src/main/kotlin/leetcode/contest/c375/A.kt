package leetcode.contest.c375

import utils.print

fun main() {
    val s = SolutionA()
    s.countTestedDevices(intArrayOf(1, 1, 2, 1, 3)).print()
}

class SolutionA {
    fun countTestedDevices(batteryPercentages: IntArray): Int {
        var ans = 0
        for (i in batteryPercentages.indices) {
            val item = batteryPercentages[i]
            if (item - ans > 0) {
                ans++
            }
        }
        return ans
    }
}