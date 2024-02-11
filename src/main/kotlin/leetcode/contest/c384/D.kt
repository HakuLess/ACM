package leetcode.contest.c384

import utils.kmpSearch
import utils.print

fun main() {
    val s = SolutionD()
    s.countMatchingSubarrays(intArrayOf(1, 2, 3, 4, 5, 6), intArrayOf(1, 1)).print()
}

class SolutionD {
    fun countMatchingSubarrays(nums: IntArray, pattern: IntArray): Int {
        val sb = StringBuilder()
        for (i in 1 until nums.size) {
            val j = i - 1
            if (nums[i] > nums[j]) {
                sb.append('a')
            } else if (nums[i] == nums[j]) {
                sb.append('b')
            } else {
                sb.append('c')
            }
        }

        val p = pattern.map {
            when (it) {
                1 -> 'a'
                0 -> 'b'
                else -> 'c'
            }
        }.joinToString("")

//        println("$sb with $p")

        var ans = 0
        kmpSearch(p, sb.toString()) {
            ans++
        }
        return ans
    }
}