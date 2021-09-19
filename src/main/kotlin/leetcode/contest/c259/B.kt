package leetcode.contest.c259

class Solution5876 {
    fun sumOfBeauties(nums: IntArray): Int {
        val maxArr = IntArray(nums.size)
        for (i in 1 until nums.size) {
            maxArr[i] = maxOf(maxArr[i - 1], nums[i - 1])
        }
        val minArr = IntArray(nums.size) { Int.MAX_VALUE }
        for (i in nums.lastIndex - 1 downTo 0) {
            minArr[i] = minOf(minArr[i + 1], nums[i + 1])
        }
        var ans = 0
        for (i in 1..nums.size - 2) {
            if (nums[i] > maxArr[i] && nums[i] < minArr[i]) {
                ans += 2
            } else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) {
                ans++
            }
        }
        return ans
    }
}