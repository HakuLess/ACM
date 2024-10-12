package leetcode.contest.b141

import utils.biMin

class SolutionA {
    fun minBitwiseArray(nums: List<Int>): IntArray {
        val n = nums.size
        val ans = IntArray(n) { -1 }

        for (i in 0 until n) {
            ans[i] = biMin(1L, nums[i].toLong()) { x ->
                x or (x + 1) <= nums[i]
            }.toInt()
//            for (x in nums[i] / 2..nums[i]) {
//                if ((x or (x + 1)) == nums[i]) {
//                    ans[i] = x
//                    break
//                }
//            }
        }
        return ans
    }
}