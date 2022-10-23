package leetcode.contest.c316

import utils.print
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionD()
    s.makeSimilar(intArrayOf(8, 12, 6), intArrayOf(2, 14, 10)).print()
}

class SolutionD {
    fun makeSimilar(nums: IntArray, target: IntArray): Long {
        val a0 = ArrayList<Int>()
        val a1 = ArrayList<Int>()
        val b0 = ArrayList<Int>()
        val b1 = ArrayList<Int>()
        for (i in nums.indices) {
            if (nums[i] % 2 == 0) {
                a0.add(nums[i])
            } else {
                a1.add(nums[i])
            }
        }
        for (i in target.indices) {
            if (target[i] % 2 == 0) {
                b0.add(target[i])
            } else {
                b1.add(target[i])
            }
        }

        a0.sort()
        a1.sort()
        b0.sort()
        b1.sort()
        var ans = 0L
        for (i in a0.indices) {
            if (b0[i] < a0[i]) {
                ans += a0[i] - b0[i]
            }
        }
        for (i in a1.indices) {
            if (b1[i] < a1[i]) {
                ans += a1[i] - b1[i]
            }
        }
        return ans / 2
    }
}