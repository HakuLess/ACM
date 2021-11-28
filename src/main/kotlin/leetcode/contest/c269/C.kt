package leetcode.contest.c269

import utils.print

fun main() {
    val s = SolutionC()
//    s.minimumDeletions(intArrayOf(2, 10, 7, 5, 4, 1, 8, 6)).print()
    s.minimumDeletions(intArrayOf(41, -47, -26, 57, 82, -23, 47, 52, 42, 79, 2, 77, 0, -4, 1, -99, -57, 72, -95))
        .print()
}

class SolutionC {
    fun minimumDeletions(nums: IntArray): Int {
        val min = intArrayOf(Int.MAX_VALUE, -1)
        val max = intArrayOf(Int.MIN_VALUE, -1)
        for (i in nums.indices) {
            if (nums[i] < min[0]) {
                min[0] = nums[i]
                min[1] = i
            }
            if (nums[i] > max[0]) {
                max[0] = nums[i]
                max[1] = i
            }
        }
        val n = nums.size
        val a = maxOf(min[1] + 1, max[1] + 1)
        val b = maxOf(n - min[1], n - max[1])
        val c = if (min[1] + 1 < max[1]) {
            min[1] + 1 + n - max[1]
        } else {
            Int.MAX_VALUE
        }
        val d = if (max[1] + 1 < min[1]) {
            n - min[1] + max[1] + 1
        } else {
            Int.MAX_VALUE
        }
//        return intArrayOf(a, b, c, d).min()!!
//        intArrayOf(a, b, c, d).print()
        return intArrayOf(a, b, c, d).minOrNull()!!
    }
}