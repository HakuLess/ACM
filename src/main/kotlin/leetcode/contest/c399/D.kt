package leetcode.contest.c399

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.maximumSumSubsequence(intArrayOf(3, 5, 9), "[[1,-2],[0,-3]]".toGrid()).print()
}

class SolutionD {
    fun maximumSumSubsequence(nums: IntArray, queries: Array<IntArray>): Int {

        fun maxNonAdjacentSum(nums: IntArray): Long {
            var incl = 0L
            var excl = 0L

            for (num in nums) {
                val newExcl = maxOf(incl, excl)
                incl = excl + num
                excl = newExcl
            }

            return maxOf(incl, excl).also {
                println("${nums.joinToString()} with $it")
            }
        }

        val mod = 1000000007L
        var currentSum = 0L
        var resultSum = currentSum

        for (query in queries) {
            val pos = query[0]
            val newValue = query[1]

            // Update nums
            nums[pos] = newValue

            // Recalculate max non-adjacent sum
            currentSum = maxNonAdjacentSum(nums)

            // Accumulate the results
            resultSum = (resultSum + currentSum) % mod
        }

        return resultSum.toInt()
    }
}