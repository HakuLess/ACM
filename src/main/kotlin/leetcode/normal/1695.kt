package leetcode.normal

class Solution1695 {
    fun maximumUniqueSubarray(nums: IntArray): Int {
        val set = HashSet<Int>()
        var ans = 0
        var sum = 0
        var left = 0
        for (right in nums.indices) {
            val cur = nums[right]
            while (cur in set) {
                set.remove(nums[left])
                sum -= nums[left]
                left++
            }

            sum += cur
            set.add(cur)
            ans = maxOf(ans, sum)
        }
        return ans
    }
}