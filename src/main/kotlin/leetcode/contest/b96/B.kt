package leetcode.contest.b96

class SolutionB {
    fun minOperations(nums1: IntArray, nums2: IntArray, k: Int): Long {
        if (k == 0) {
            return if (nums1.joinToString() == nums2.joinToString()) 0L
            else -1L
        }
        var a = 0L
        var b = 0L
        for (i in nums1.indices) {
            if ((nums1[i] - nums2[i]) % k != 0) return -1
            ((nums1[i] - nums2[i]) / k).let {
                if (it >= 0) a += it
                else b += it
            }
        }
        return if (a + b == 0L) a
        else -1L
    }
}