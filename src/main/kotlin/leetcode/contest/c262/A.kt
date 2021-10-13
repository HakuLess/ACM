package leetcode.contest.c262

class Solution5894 {
    fun twoOutOfThree(nums1: IntArray, nums2: IntArray, nums3: IntArray): List<Int> {
        val ans = hashSetOf<Int>()
        ans.addAll(nums1.intersect(nums2.toList()))
        ans.addAll(nums2.intersect(nums3.toList()))
        ans.addAll(nums1.intersect(nums3.toList()))
        return ans.toList()
    }
}