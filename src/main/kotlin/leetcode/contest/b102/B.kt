package leetcode.contest.b102

import utils.preSumArray
import utils.print

fun main() {
    val s = SolutionB()
    s.findPrefixScore(intArrayOf(2, 3, 7, 5, 10)).print()
}

class SolutionB {
    fun findPrefixScore(nums: IntArray): LongArray {
        val n = nums.size
        val cur = LongArray(n)
        var max = 0L
        for (i in nums.indices) {
            max = maxOf(nums[i].toLong(), max)
            cur[i] = max + nums[i]
        }
        return cur.preSumArray(false)
    }
}