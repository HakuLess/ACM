package leetcode.contest.c305

import utils.print

fun main() {
    val s = SolutionC()
    s.validPartition(intArrayOf(4, 4, 4, 5, 6)).print()
}

class SolutionC {
    fun validPartition(nums: IntArray): Boolean {
        if (nums.size == 2) {
            return nums[0] == nums[1]
        }
        val b = BooleanArray(nums.size)
        if (nums[2] == nums[0] + 2 && nums[2] == nums[1] + 1) {
            b[2] = true
        }
        if (nums[0] == nums[1]) {
            b[1] = true
        }
        if (nums[0] == nums[1] && nums[1] == nums[2]) {
            b[2] = true
        }
        if (b.all { !it }) return false
        for (i in 3 until nums.size) {
            if (nums[i] == nums[i - 1]) {
                b[i] = b[i] or b[i - 2]
            }
            if (nums[i] == nums[i - 1] + 1 && nums[i] == nums[i - 2] + 2) {
                b[i] = b[i] or b[i - 3]
            }
            if (nums[i] == nums[i - 1] && nums[i] == nums[i - 2]) {
                b[i] = b[i] or b[i - 3]
            }
        }
        return b.last()
    }
}