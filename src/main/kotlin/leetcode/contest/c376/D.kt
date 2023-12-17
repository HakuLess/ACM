package leetcode.contest.c376

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionD()
//    s.maxFrequencyScore(intArrayOf(1, 2, 6, 4), 3L).print()
//    s.maxFrequencyScore(intArrayOf(1, 4, 4, 2, 4), 0L).print()
    // 14
    s.maxFrequencyScore(intArrayOf(13, 22, 29, 21, 13, 17, 5, 2, 27, 6, 10, 4, 23, 29, 27), 117L).print()
}

class SolutionD {

    fun maxFrequencyScore(nums: IntArray, k: Long): Int {
        nums.sort()
        val n = nums.size
        val s = LongArray(n + 1)

        for (i in 0 until n) {
            s[i + 1] = s[i] + nums[i]
        }

        var l = 1
        var r = n + 1
        var mid: Int

        while (l + 1 < r) {
            mid = (l + r) ushr 1
            var found = false

            for (i in 0 until n - mid + 1) {
                val sum = s[i + mid] - s[i + (mid shr 1)] * 2 + s[i] - nums[i + (mid shr 1)] * (mid and 1).toLong()
                if (sum <= k) {
                    found = true
                    break
                }
            }

            if (found) {
                l = mid
            } else {
                r = mid
            }
        }

        return l
    }
}