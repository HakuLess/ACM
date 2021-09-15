package leetcode.normal

class Solution162 {
    fun findPeakElement(nums: IntArray): Int {
        val arr = nums.map { it.toLong() }
        for (i in arr.indices) {
            if (arr[i] > arr.getOrElse(i + 1) { Long.MIN_VALUE }
                && arr[i] > arr.getOrElse(i - 1) { Long.MIN_VALUE })
                return i
        }
        return -1
    }
}