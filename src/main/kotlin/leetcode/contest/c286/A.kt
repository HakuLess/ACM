package leetcode.contest.c286

class SolutionA {
    fun findDifference(nums1: IntArray, nums2: IntArray): List<List<Int>> {
        val a = nums1.filter { it !in nums2 }.distinct()
        val b = nums2.filter { it !in nums1 }.distinct()
        return listOf(a, b)
    }
}