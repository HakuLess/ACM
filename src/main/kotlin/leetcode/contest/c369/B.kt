package leetcode.contest.c369

class SolutionB {
    fun minSum(nums1: IntArray, nums2: IntArray): Long {
        var sum1 = 0L
        var c1 = 0
        for (i in nums1.indices) {
            if (nums1[i] == 0) c1++
            else sum1 += nums1[i]
        }
        var sum2 = 0L
        var c2 = 0
        for (i in nums2.indices) {
            if (nums2[i] == 0) c2++
            else sum2 += nums2[i]
        }
        if (c1 == 0 && sum2 + c2 > sum1) return -1L
        if (c2 == 0 && sum1 + c1 > sum2) return -1L
        return maxOf(sum1 + c1, sum2 + c2)
    }
}