package leetcode.normal

class Solution560 {
    fun subarraySum(nums: IntArray, k: Int): Int {
        var ans = 0
        for (i in nums.indices) {
            var sum = 0
            for (j in i until nums.size) {
                sum += nums[j]
                if (sum == k) {
                    ans++
                }
            }
        }
        return ans
    }
}