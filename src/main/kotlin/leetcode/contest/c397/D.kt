package leetcode.contest.c397

import utils.permuteUnique
import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionD()
    s.findPermutation(intArrayOf(1, 0, 2)).print()
    s.findPermutation(intArrayOf(0, 2, 1)).print()
}

class SolutionD {
    fun findPermutation(nums: IntArray): IntArray {
//        val n = nums.size
//
//        fun score(perm: IntArray): Int {
//            var result = 0
//            for (i in 0 until n) {
//                result += abs(perm[i] - nums[perm[(i + 1) % n]])
//            }
//            return result
//        }
//
//        var minV = Int.MAX_VALUE / 2
//        var ans = intArrayOf()
//        nums.permuteUnique().forEach {
//            val score = score(it.toIntArray())
//            println("${it.joinToString()} with $score")
//            if (score < minV) {
//                minV = score
//                ans = it.toIntArray()
//            }
//        }
//        return ans

        return intArrayOf()
    }
}