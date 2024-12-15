package leetcode.contest.c428

import utils.hash
import utils.print

fun main() {
    val s = SolutionC()
//    s.beautifulSplits(intArrayOf(1, 50, 1, 50, 50)).print()
//    s.beautifulSplits(intArrayOf(1, 1, 2, 1)).print()
//    s.beautifulSplits(intArrayOf(2, 3, 2, 2, 1)).print()
    // 22
    s.beautifulSplits(intArrayOf(2, 2, 0, 0, 0, 0, 0, 1, 2, 2, 0, 0, 0, 1, 0)).print()
}

class SolutionC {
    fun beautifulSplits(nums: IntArray): Int {
        val n = nums.size
        var count = 0

        val sb = StringBuilder()
        for (i in nums.indices) {
            sb.append('A' + nums[i])
        }
        val str = sb.toString()
        val strHash = str.hash()

        fun isPrefix(start1: Int, end1: Int, start2: Int, end2: Int): Boolean {
            if (end1 - start1 > end2 - start2) return false
            val len = end1 - start1 - 1
            return strHash.hash(start1, end1 - 1) == strHash.hash(start2, start2 + len)
        }

        for (i in 1 until n - 1) {
            for (j in i + 1 until n) {
                if (isPrefix(0, i, i, j) || isPrefix(i, j, j, n)) {
                    count++
                }
            }
        }

        return count
    }
}