package leetcode.normal

import utils.swap

class Solution75 {
    fun sortColors(nums: IntArray): Unit {
        var p0 = 0
        var p1 = 0
        for (i in nums.indices) {
            val item = nums[i]

            if (item == 1) {
                nums.swap(i, p1)
                p1++
            } else if (item == 0) {
                nums.swap(i, p0)
                if (p0 < p1) {
                    nums.swap(i, p1)
                }
                p0++
                p1++
            }
        }
    }
}