package leetcode.contest.c353

import utils.print

fun main() {
    val s = SolutionD()
    s.checkArray(intArrayOf(2, 2, 3, 1, 1, 0), 3).print()
    s.checkArray(intArrayOf(1, 3, 1, 1), 2).print()
}

class SolutionD {
    fun checkArray(nums: IntArray, k: Int): Boolean {
        var cur = 0
        val diff = IntArray(nums.size)
        for (i in nums.indices) {
//            println("$i : ${nums[i]} $cur")
            if (nums[i] != cur) {
                diff[i] += nums[i] - cur
                if (i + k - 1 !in nums.indices) return false
                diff[i + k - 1] -= nums[i] - cur
            }
//            println("$i ${diff.joinToString()}")
            if (nums[i] < cur) return false
            cur += diff[i]
        }
        return true
    }
}