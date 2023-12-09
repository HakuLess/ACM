package leetcode.contest.b119

class SolutionA {
    fun findIntersectionValues(nums1: IntArray, nums2: IntArray): IntArray {
        var a = nums1.count { it in nums2 }
        var b = nums2.count { it in nums1 }
        return intArrayOf(a, b)
    }
}