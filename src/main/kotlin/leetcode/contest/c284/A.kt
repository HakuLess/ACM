package leetcode.contest.c284

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionA()
    s.findKDistantIndices(intArrayOf(3, 4, 9, 1, 3, 9, 5), 9, 1).joinToString().print()
    s.findKDistantIndices(intArrayOf(2, 2, 2, 2, 2), 2, 2).joinToString().print()
}

class SolutionA {
    fun findKDistantIndices(nums: IntArray, key: Int, k: Int): List<Int> {
        val ans = arrayListOf<Int>()
        for (i in nums.indices) {
            for (j in nums.indices) {
                if (nums[i] == key && abs(i - j) <= k) {
                    ans.add(j)
                }
            }
        }
        return ans.distinct()
    }
}