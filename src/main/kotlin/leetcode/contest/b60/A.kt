package leetcode.contest.b60

import utils.print

fun main() {
    val s = Solution5846()
    s.findMiddleIndex(intArrayOf(2, 3, -1, 8, 4)).print()
}

class Solution5846 {
    fun findMiddleIndex(nums: IntArray): Int {
        val left = IntArray(nums.size + 1)
        val right = IntArray(nums.size + 1)
        for (i in nums.indices) {
            left[i + 1] = left[i] + nums[i]
        }
        for (i in nums.indices.reversed()) {
            right[i] = right[i + 1] + nums[i]
        }
        left.print()
        right.print()
        for (i in nums.indices) {
            if (left[i] == right[i + 1]) {
                return i
            }
        }
        return -1
    }
}