package leetcode.normal

import kotlin.random.Random

class Solution384(val nums: IntArray) {

    val reset = nums.clone()

    fun reset(): IntArray {
        return reset
    }

    fun shuffle(): IntArray {
        nums.shuffle(Random.Default)
        return nums
    }

}

/**
 * Your Solution object will be instantiated and called as such:
 * var obj = Solution(nums)
 * var param_1 = obj.reset()
 * var param_2 = obj.shuffle()
 */