package leetcode.contest.b96

class SolutionA {
    fun getCommon(nums1: IntArray, nums2: IntArray): Int {
        return nums1.firstOrNull { it in nums2 } ?: -1
    }
}