package leetcode.contest.c283

import utils.print

fun main() {
    val s = SolutionB()
    s.minimalKSum(intArrayOf(96,44,99,25,61,84,88,18,19,33,60,86,52,19,32,47,35,50,94,17,29,98,22,21,72,100,40,84), 35).print()
}

class SolutionB {
    fun minimalKSum(nums: IntArray, k: Int): Long {
        var ans = 1L * k * (k + 1) / 2
        val n = nums.distinct().sorted()
        var cur = k
        for (i in n.indices) {
            if (n[i] <= cur) {
                cur++
                ans -= n[i]
                ans += cur
            }
        }
        return ans
    }
}