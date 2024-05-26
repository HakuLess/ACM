package leetcode.contest.c399

class SolutionA {
    fun numberOfPairs(nums1: IntArray, nums2: IntArray, k: Int): Int {
        var ans = 0
        for (i in nums1.indices) {
            for (j in nums2.indices) {
                if (nums1[i] % (nums2[j] * k) == 0) {
                    ans++
                }
            }
        }
        return ans
    }
}