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
        val ans = ArrayList<Int>()
        var r = 0
        val n = nums.size
        for (j in nums.indices) {
            if (nums[j] == key) {
                val l = maxOf(r, j - k)
                r = minOf(n - 1, j + k) + 1
                for (i in l until r) {
                    ans.add(i)
                }
            }
        }
        return ans
    }
}