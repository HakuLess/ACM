package leetcode.contest.c307

import utils.print

fun main() {
    val s = SolutionA()
    s.minNumberOfHours(5, 3, intArrayOf(1, 4, 3, 2), intArrayOf(2, 6, 3, 1)).print()
}

class SolutionA {
    fun minNumberOfHours(initialEnergy: Int, initialExperience: Int, energy: IntArray, experience: IntArray): Int {
        var ans = 0
        ans += maxOf(energy.sum() + 1 - initialEnergy, 0)
        var tmp = 0
        var cur = initialExperience
        for (i in experience.indices) {
            if (cur > experience[i]) {

            } else {
                tmp = maxOf(tmp, experience[i] + 1 - cur)
            }

            cur += experience[i]
            println("$i: $tmp")
        }
        ans += tmp
        return ans
    }
}