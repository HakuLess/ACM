package leetcode.normal

class Solution3190 {
    fun minimumOperations(nums: IntArray): Int {
        return nums.sumOf {
            val mod = it % 3
            minOf(mod, 3 - mod)
        }
    }
}