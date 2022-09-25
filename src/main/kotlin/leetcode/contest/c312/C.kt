package leetcode.contest.c312

import utils.print

fun main() {
    val s = SolutionC()
    // [2, 3]
//    s.goodIndices(intArrayOf(2, 1, 1, 1, 3, 4, 1), 2).joinToString().print()
//    // []
//    s.goodIndices(intArrayOf(2, 1, 1, 2), 2).joinToString().print()
//    // [4, 5]
//    s.goodIndices(intArrayOf(878724, 201541, 179099, 98437, 35765, 327555, 475851, 598885, 849470, 943442), 4)
//        .joinToString().print()

    s.goodIndices(intArrayOf(440043, 276285, 336957), 1).joinToString().print()
}

class SolutionC {
    fun goodIndices(nums: IntArray, k: Int): List<Int> {
        val left = BooleanArray(nums.size) { false }
        val right = BooleanArray(nums.size) { false }

        if (k == 1) {
            for (i in 1 until nums.size - 1) {
                left[i] = true
                right[i] = true
            }
        }

        var pre = 0
        var cur = 1
        for (i in nums.indices) {
            if (i == 0) {
                pre = nums[i]
                continue
            } else {
                if (nums[i] <= pre) {
                    cur++
                } else {
                    cur = 1
                }
            }
            if (cur >= k) {
                if (i + 1 in left.indices)
                    left[i + 1] = true
            }
            pre = nums[i]
        }

        cur = 1
        for (i in nums.indices.reversed()) {
            if (i == nums.lastIndex) {
                pre = nums[i]
                continue
            } else {
                if (nums[i] <= pre) {
                    cur++
                } else {
                    cur = 1
                }
            }
            if (cur >= k) {
                if (i - 1 in nums.indices)
                    right[i - 1] = true
            }
            pre = nums[i]
        }

        left.print()
        right.print()

        val ans = ArrayList<Int>()
        for (i in nums.indices) {
            if (left[i] && right[i]) {
                ans.add(i)
            }
        }
        return ans
    }
}