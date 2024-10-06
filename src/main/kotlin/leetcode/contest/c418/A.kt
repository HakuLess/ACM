package leetcode.contest.c418

import utils.permuteUnique

class SolutionA {
    fun maxGoodNumber(nums: IntArray): Int {
        val permutations = nums.permuteUnique()
        var ans = 0

        for (item in permutations) {
            val str = item.joinToString(separator = "") { it.toString(2) }
            val tmp = str.toInt(2)
            ans = maxOf(ans, tmp)
        }

        return ans
    }
}