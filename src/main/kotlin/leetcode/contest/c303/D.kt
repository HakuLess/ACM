package leetcode.contest.c303

import utils.print

fun main() {
    val s = SolutionD()
    s.countExcellentPairs(intArrayOf(1, 2, 3, 1), 3).print()
    s.countExcellentPairs(intArrayOf(5, 1, 1), 10).print()
    // 21
    s.countExcellentPairs(intArrayOf(134217727, 7, 268435455, 3, 536870911, 1), 30).print()
}

class SolutionD {
    fun countExcellentPairs(nums: IntArray, k: Int): Long {
        val set = nums.distinct().map {
            it.toString(2).count { it == '1' }
        }.sorted()
        var ans = 0L
        var r = set.lastIndex
        for (l in set.indices) {
            while (r in set.indices && set[r] + set[l] >= k) {
                r--
            }
            ans += (set.size - r - 1)
        }
        return ans
    }
}