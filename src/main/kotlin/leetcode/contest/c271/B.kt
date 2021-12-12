package leetcode.contest.c271

import utils.print

fun main() {
    val s = SolutionB()
    s.subArrayRanges(intArrayOf(1, 2, 3)).print()
}

class SolutionB {
    fun subArrayRanges(nums: IntArray): Long {
        fun getBound(l: Int, r: Int): Long {
            var min = Long.MAX_VALUE
            var max = Long.MIN_VALUE
            for (i in l..r) {
                max = maxOf(max, nums[i].toLong())
                min = minOf(min, nums[i].toLong())
            }
            return max - min
        }

        var ans = 0L
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (i == j) continue
                ans += getBound(i, j).also {
                    println("$i, $j, $it")
                }
            }
        }
        return ans
    }
}