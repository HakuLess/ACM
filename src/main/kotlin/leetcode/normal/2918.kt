package leetcode.normal

class Solution2918 {
    fun minSum(nums1: IntArray, nums2: IntArray): Long {
        var sum1 = 0L
        var sum2 = 0L
        var c01 = 0
        var c02 = 0
        for (i in nums1.indices) {
            if (nums1[i] == 0) {
                sum1 += 1L
                c01++
            } else {
                sum1 += nums1[i]
            }
        }
        for (i in nums2.indices) {
            if (nums2[i] == 0) {
                sum2 += 1L
                c02++
            } else {
                sum2 += nums2[i]
            }
        }
        if (c01 > 0 && c02 > 0) {
            return maxOf(sum1, sum2)
        }
        if (c01 == 0 && c02 == 0) {
            if (sum1 != sum2) return -1
            else return sum1
        }
        if (c01 > 0 && c02 == 0) {
            if (sum1 <= sum2) return sum2
            else return -1
        }
        if (c01 == 0 && c02 > 0) {
            if (sum2 <= sum1) return sum1
            else return -1
        }
        return -1
    }
}