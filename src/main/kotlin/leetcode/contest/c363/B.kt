package leetcode.contest.c363

import utils.print

fun main() {
    val s = SolutionB()
//    s.countWays(listOf(1, 1, 0, 1)).print()
    s.countWays(listOf(6, 0, 3, 3, 6, 7, 2, 7)).print()
    s.countWays(listOf(1, 1)).print()
}

class SolutionB {
    fun countWays(nums: List<Int>): Int {
        var ans = 0
        val l = nums.sorted()
        var cur = 0
        for (i in l.indices) {
            cur++
            if (cur > l[i]) {
                if (i + 1 in l.indices) {
                    if (l[i + 1] > cur) {
                        ans++
                    }
                } else {
                    ans++
                }
            }
        }
        return ans + if (nums.all { it > 0 }) 1 else 0
    }
}