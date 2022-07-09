package leetcode.contest.b82

import utils.print
import java.util.*
import kotlin.math.abs

fun main() {
    val s = SolutionC()
//    s.minSumSquareDiff(intArrayOf(1, 2, 3, 4), intArrayOf(2, 10, 20, 19), 0, 0).print()
//    s.minSumSquareDiff(intArrayOf(1, 4, 10, 12), intArrayOf(5, 8, 6, 9), 1, 1).print()
//    s.minSumSquareDiff(intArrayOf(1, 4, 10, 12), intArrayOf(5, 8, 6, 9), 1, 100).print()
    s.minSumSquareDiff(intArrayOf(19, 18, 19, 18, 18, 19, 19), intArrayOf(1, 0, 1, 0, 0, 1, 1), 10, 33).print()
}

class SolutionC {
    fun minSumSquareDiff(nums1: IntArray, nums2: IntArray, k1: Int, k2: Int): Long {
        val diff = LongArray(nums1.size + 1)
        for (i in nums1.indices) {
            diff[i] = abs(0L + nums1[i] - nums2[i])
        }
        diff.sortDescending()
        diff.print()
        var left = 0L + k1 + k2
        var sum = 0L
        var index = -1
        for (i in diff.indices) {
//            println("$sum ${sum - diff[i] * i}")
            if (sum - diff[i] * i <= left) {
                // 左侧值全变为当前值
            } else {
                println("break $i")
                index = i - 1
                break
            }
            sum += diff[i]
        }
        if (left >= sum) return 0L
        println("diff index ${diff[index]}")
        for (i in 0 until index) {
            left -= diff[i] - diff[index]
            diff[i] = diff[index]
        }
        val a = left / (index + 1)
        val b = left % (index + 1)

        println("diff left $left $a $b")
        for (i in 0..index) {
            if (i in 0 until b) {
                diff[i] -= a + 1
            } else {
                diff[i] -= a
            }
        }
        diff.print()
        var ans = 0L
        for (i in diff.indices) {
            ans += diff[i] * diff[i]
        }
        return ans
    }
}