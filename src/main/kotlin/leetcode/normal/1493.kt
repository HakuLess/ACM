package leetcode.normal

class Solution1493 {
    fun longestSubarray(nums: IntArray): Int {
        if (nums.all { it == 1 }) return nums.size - 1
        var cur = 0
        val l = ArrayList<Int>()
        for (i in nums.indices) {
            if (nums[i] == 1) {
                cur++
            } else {
                l.add(cur)
                cur = 0
            }
        }
        l.add(cur)
        var ans = 0
        for (i in 0 until l.lastIndex) {
            ans = maxOf(ans, l[i] + l[i + 1])
        }
        return ans
    }
}